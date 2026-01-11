import { test, expect } from '@playwright/test';

// Helpers to stub the backend login API
const API_BASE = 'http://localhost:4000/api/user';

test('redirects to profile on successful login and stores user without password', async ({ page }) => {
  await page.route(`${API_BASE}/login`, async route => {
    const request = route.request();
    const body = await request.postDataJSON();
    // Validate that credentials were sent (username & password present)
    expect(body.username).toBeTruthy();
    expect(body.password).toBeTruthy();

    await route.fulfill({
      status: 200,
      contentType: 'application/json',
      body: JSON.stringify({ id: 1, username: body.username, email: `${body.username}@example.com`, password: 'secret' })
    });
  });

  await page.goto('/');
  await page.fill('#username', 'alice');
  await page.fill('#password', 's3cret');
  await page.click('button[type="submit"]');

  await page.waitForURL('**/profile.html');
  const href = page.url();
  expect(href.endsWith('/profile.html')).toBe(true);

  // Ensure sessionStorage has user without password
  const stored = await page.evaluate(() => sessionStorage.getItem('user'));
  const parsed = JSON.parse(stored as string);
  expect(parsed.username).toBe('alice');
  expect(parsed.password).toBeUndefined();
});

test('shows error and does not persist user on failed login', async ({ page }) => {
  await page.route(`${API_BASE}/login`, async route => {
    await route.fulfill({ status: 401, body: 'unauthorized' });
  });

  await page.goto('/');
  await page.fill('#username', 'alice');
  await page.fill('#password', 'bad');
  await page.click('button[type="submit"]');

  // Should remain on the same page and show error message
  await expect(page.locator('text=Usuario o contrasena equivocados')).toBeVisible();
  expect(page.url().endsWith('/profile.html')).toBe(false);

  const stored = await page.evaluate(() => sessionStorage.getItem('user'));
  expect(stored).toBeNull();
});
