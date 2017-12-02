package me.leduyhung.mdaily.observer.listener;

/**
 * Created by hungleduy on 11/6/17.
 */

public class UserEvent {

    public interface UserLogin {

        void onLoginComplete();

        void onLoginCancel();

        void onLoginFail();
    }

    public interface UserLogout {

        void onLogoutComplete();
    }
}
