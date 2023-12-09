package com.swing.page;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts;
import com.swing.Persistent.MysqlPersistentSettings;
import com.swing.Persistent.MysqlProperties;
import com.swing.jpanel.MysqlSettingUi;
import com.swing.utils.AlertUtil;
import com.swing.utils.JdbcUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class EntityBuilderPage implements Configurable {
    public MysqlSettingUi entityFormUi = new MysqlSettingUi();

    public EntityBuilderPage(){
        MysqlPersistentSettings instance = MysqlPersistentSettings.getInstance();
        entityFormUi.getUrl().setText(instance.url);
        entityFormUi.getUserName().setText(instance.userName);
        entityFormUi.getMyPassword().setText(instance.myPassword);
        MysqlProperties.url = instance.url;
        MysqlProperties.myPassword = instance.myPassword;
        MysqlProperties.userName = instance.userName;
    }


    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "entity builder settings";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return entityFormUi.getComponent();
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() {
        entityFormUi.getConnectMsg().setText("");
        JTextField url = entityFormUi.getUrl();
        JTextField userName = entityFormUi.getUserName();
        JTextField password = entityFormUi.getMyPassword();
        MysqlPersistentSettings instance = MysqlPersistentSettings.getInstance();
        instance.url = url.getText();
        instance.userName = userName.getText();
        instance.myPassword = password.getText();
        MysqlProperties.url = url.getText();
        MysqlProperties.userName = userName.getText();
        MysqlProperties.myPassword = password.getText();
        if(!MysqlProperties.isInit()){
            AlertUtil.showText("请先维护数据源信息");
            return ;
        }
        boolean result = JdbcUtil.testMysqlConnect();
        if(!result){
            entityFormUi.getConnectMsg().setText("连接mysql数据库失败!");
        }else {
            entityFormUi.getConnectMsg().setText("连接mysql数据库成功!");
        }
    }
}
