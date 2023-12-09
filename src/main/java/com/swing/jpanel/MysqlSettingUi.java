package com.swing.jpanel;

import javax.swing.*;

public class MysqlSettingUi {
    private JPanel mysqlJPanel;
    private JTextField url;

    private JTextField userName;
    private JTextField myPassword;
    private JLabel connectMsg;

   /* public MysqlSettingUi(){
        testMysql.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(!MysqlProperties.isInit()){
                    AlertUtil.showText("请先应用数据源信息");
                    return ;
                }
                boolean result = JdbcUtil.testMysqlConnect();
                if(!result){
                    AlertUtil.showText("连接失败");
                }else {
                    AlertUtil.showText("连接成功");
                }
            }
        });
    }*/

    public JPanel getMysqlJPanel() {
        return mysqlJPanel;
    }

    public void setMysqlJPanel(JPanel mysqlJPanel) {
        this.mysqlJPanel = mysqlJPanel;
    }

    public JTextField getUrl() {
        return url;
    }

    public void setUrl(JTextField url) {
        this.url = url;
    }

    public JTextField getUserName() {
        return userName;
    }

    public void setUserName(JTextField userName) {
        this.userName = userName;
    }

    public JTextField getMyPassword() {
        return myPassword;
    }

    public void setMyPassword(JTextField password) {
        this.myPassword = password;
    }
    public JComponent getComponent() {
        return mysqlJPanel;
    }

    public JLabel getConnectMsg() {
        return connectMsg;
    }

    public void setConnectMsg(JLabel connectMsg) {
        this.connectMsg = connectMsg;
    }
}
