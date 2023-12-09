package com.swing.action;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.swing.Persistent.MysqlPersistentSettings;
import com.swing.Persistent.MysqlProperties;
import com.swing.model.TableDto;
import com.swing.utils.AlertUtil;
import com.swing.utils.CreateEntityUtil;
import com.swing.utils.JdbcUtil;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ToolWindowEntityButton extends AnAction {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        Project project = anActionEvent.getData(PlatformDataKeys.PROJECT);
        MysqlPersistentSettings.getInstance();
        boolean init = MysqlProperties.isInit();
        if(!init){
            AlertUtil.showText("请先维护数据源Settings->Easy Entity");
            return;
        }
        VirtualFile file = anActionEvent.getData(PlatformDataKeys.VIRTUAL_FILE);
        String path = null;
        if (file != null && file.isDirectory()) {
            path = file.getPath();
        }
        // 创建弹出窗口的内容
        String tableName = JOptionPane.showInputDialog(null, "请输入表名：");
        if(StringUtils.isBlank(tableName)){
            return;
        }
        if(StringUtils.isBlank(path)){
            return;
        }
        try {
            String database = CreateEntityUtil.getDatabase(MysqlPersistentSettings.getInstance().url);
            TableDto tableDto = JdbcUtil.executeSql(tableName,database);
            // 生成entity
           if(tableDto == null){
               return;
           }
            CreateEntityUtil.createEntity(tableDto,path);
            String className = CreateEntityUtil.getFormatName(tableDto.getTableName(),true);
           AlertUtil.showTip("create "+className+"Entity success!");
            VirtualFile dir = LocalFileSystem.getInstance().findFileByPath(path);
            dir.refresh(false,true);
        } catch (Exception e) {
            AlertUtil.showTip("数据库连接失败，请检查！");
            throw new RuntimeException(e);
        }
    }

}
