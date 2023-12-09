package com.swing.Persistent;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "mysqlPersistentSettings", storages = {@Storage(value = "mysqlPersistentSettings.xml")})
public class MysqlPersistentSettings implements PersistentStateComponent<MysqlPersistentSettings> {

    public String url;
    public String userName;
    public String myPassword;

    public static MysqlPersistentSettings getInstance() {
        return ApplicationManager.getApplication().getService(MysqlPersistentSettings.class);
    }

    @Override
    public @Nullable MysqlPersistentSettings getState() {
/*        MysqlProperties.url = this.url;
        MysqlProperties.userName = this.userName;
        MysqlProperties.password = this.password;*/
        return this;
    }

    @Override
    public void loadState(@NotNull MysqlPersistentSettings mysqlPersistentSettings) {
        this.url = mysqlPersistentSettings.url;
        this.userName = mysqlPersistentSettings.userName;
        this.myPassword = mysqlPersistentSettings.myPassword;
        MysqlProperties.url = mysqlPersistentSettings.url;
        MysqlProperties.userName = mysqlPersistentSettings.userName;
        MysqlProperties.myPassword = mysqlPersistentSettings.myPassword;
    }


}
