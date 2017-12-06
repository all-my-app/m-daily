package me.leduyhung.mdaily.observer;

import me.leduyhung.mdaily.observer.listener.ActionBarEvent;
import me.leduyhung.mdaily.observer.listener.UserEvent;

/**
 * Created by hungleduy on 11/4/17.
 */

public class UiObserver {

    private static UiObserver uiObserver;
    private ActionBarEvent actionBarEvent;
    private UserEvent.UserLogin userLoginEvent;
    private UserEvent.UserLogout userLogoutEvent;

    public static UiObserver newInstance() {

        if (uiObserver == null) {

            uiObserver = new UiObserver();
        }
        return uiObserver;
    }

    public void listener(Object listener) {

        if (listener instanceof UserEvent.UserLogin) {

            this.userLoginEvent = (UserEvent.UserLogin) listener;
        } else if (listener instanceof UserEvent.UserLogout) {

            this.userLogoutEvent = (UserEvent.UserLogout) listener;
        } else if (listener instanceof ActionBarEvent)
            this.actionBarEvent = (ActionBarEvent) listener;
    }

    public void notify(String tag) {

        switch (tag) {

            case ObserverTag.TAG_USER_LOGIN_COMPLETE:
                if (userLoginEvent != null)
                    userLoginEvent.onLoginComplete();
                break;
            case ObserverTag.TAG_USER_LOGOUT_EVENT:
                if (userLogoutEvent != null)
                    userLogoutEvent.onLogoutComplete();
            case ObserverTag.TAG_ICON_LEFT_ACTION_BAR_CLICK:
                if (actionBarEvent != null)
                    actionBarEvent.onImgLeftClick();
                break;
            case ObserverTag.TAG_ICON_RIGHT_ACTION_BAR_CLICK:
                if (actionBarEvent != null)
                    actionBarEvent.onImgRightClick();
                break;
        }
    }
}