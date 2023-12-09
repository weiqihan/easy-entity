package com.swing.jpanel;

import com.swing.utils.AlertUtil;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EntityFormUi {
    private JTextField tableName;
    private JButton createButton;
    private JTextArea entityClassText;
    private JPanel entityJpanel;

    public EntityFormUi(){
        createButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("触发点击事件");
                AlertUtil.showTip("点击了");
            }
        });
    }
    public JTextField getTableName() {
        return tableName;
    }

    public void setTableName(JTextField tableName) {
        this.tableName = tableName;
    }

    public JButton getCreateButton() {
        return createButton;
    }

    public void setCreateButton(JButton createButton) {
        this.createButton = createButton;
    }

    public JTextArea getEntityClassText() {
        return entityClassText;
    }

    public void setEntityClassText(JTextArea entityClassText) {
        this.entityClassText = entityClassText;
    }

    public JPanel getEntityJpanel() {
        return entityJpanel;
    }

    public void setEntityJpanel(JPanel entityJpanel) {
        this.entityJpanel = entityJpanel;
    }
    public JComponent getComponent() {
        return entityJpanel;
    }

}
