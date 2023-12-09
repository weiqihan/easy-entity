package com.swing.utils;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.Messages;

/**
 * 模块：
 * AlertUtils
 * han
 * 2023/6/17
 */
public class AlertUtil {
    public static void showText(String text){
        Messages.showMessageDialog(text,"easy entity", Messages.getInformationIcon());
    }
    public static void showTip(String text){
        Notification notification = new Notification(
                "easy entity",
                "Tip",
                text,
                NotificationType.INFORMATION
        );
        Notifications.Bus.notify(notification);
    }
}
