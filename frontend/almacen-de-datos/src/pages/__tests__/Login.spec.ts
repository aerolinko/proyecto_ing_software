import { mount, flushPromises } from '@vue/test-utils';
import { describe, it, expect, vi, beforeEach } from 'vitest';
import Login from '../Login.vue';
import UserService from '@/services/UserService';

vi.mock('@/services/UserService', () => ({
  default: {
    getUserByUsername: vi.fn()
  }
}));

const mockedUserService = UserService as unknown as {
  getUserByUsername: ReturnType<typeof vi.fn>;
};

const mockBrowserLocation = () => {
  const locationState = { href: 'http://localhost' };
  Object.defineProperty(window, 'location', {
    configurable: true,
    value: {
      get href() {
        return locationState.href;
      },
      set href(next: string) {
        locationState.href = next;
      },
      assign: vi.fn((next: string) => {
        locationState.href = next;
      })
    }
  });
};

describe('Login.vue security behavior', () => {
  beforeEach(() => {
    sessionStorage.clear();
    vi.clearAllMocks();
    mockBrowserLocation();
  });

  it('sends the provided credentials to the login endpoint', async () => {
    mockedUserService.getUserByUsername.mockResolvedValue({ data: { username: 'alice' } });
    const wrapper = mount(Login);
    wrapper.vm.user.username = 'alice';
    wrapper.vm.user.password = 's3cret';

    await wrapper.vm.handleSubmit();

    expect(mockedUserService.getUserByUsername).toHaveBeenCalledWith(
      expect.objectContaining({ username: 'alice', password: 's3cret' })
    );
  });

  it('removes the password before persisting the user in sessionStorage', async () => {
    mockedUserService.getUserByUsername.mockResolvedValue({
      data: { id: 1, username: 'alice', email: 'alice@example.com', password: 's3cret' }
    });
    const wrapper = mount(Login);
    wrapper.vm.user.username = 'alice';
    wrapper.vm.user.password = 's3cret';

    await wrapper.vm.handleSubmit();
    await flushPromises();

    const stored = sessionStorage.getItem('user');
    expect(stored).not.toBeNull();
    const parsed = JSON.parse(stored as string);
    expect(parsed.password).toBeUndefined();
    expect(parsed.username).toBe('alice');
  });

  it('redirects to the profile page only after a successful login', async () => {
    mockedUserService.getUserByUsername.mockResolvedValue({ data: { username: 'alice' } });
    const wrapper = mount(Login);
    wrapper.vm.user.username = 'alice';
    wrapper.vm.user.password = 's3cret';

    await wrapper.vm.handleSubmit();
    await flushPromises();

    expect(window.location.href.endsWith('/profile.html')).toBe(true);
  });

  it('shows a generic error and avoids persisting sensitive data on failure', async () => {
    mockedUserService.getUserByUsername.mockRejectedValue(new Error('unauthorized'));
    const wrapper = mount(Login);
    wrapper.vm.user.username = 'alice';
    wrapper.vm.user.password = 'bad';

    await wrapper.vm.handleSubmit();
    await flushPromises();

    expect(wrapper.vm.errorMessage).toBe('Usuario o contrasena equivocados');
    expect(sessionStorage.getItem('user')).toBeNull();
    expect(window.location.href.endsWith('/profile.html')).toBe(false);
  });

  it('scrubs the password from component state after login', async () => {
    mockedUserService.getUserByUsername.mockResolvedValue({
      data: { id: 1, username: 'alice', email: 'alice@example.com', password: 's3cret' }
    });
    const wrapper = mount(Login);
    wrapper.vm.user.username = 'alice';
    wrapper.vm.user.password = 's3cret';

    await wrapper.vm.handleSubmit();
    await flushPromises();

    expect(wrapper.vm.user.password).toBeUndefined();
  });
});
