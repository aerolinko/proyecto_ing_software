import { afterEach, vi } from 'vitest';

// Reset shared test state between runs
afterEach(() => {
  sessionStorage.clear();
  vi.restoreAllMocks();
});
