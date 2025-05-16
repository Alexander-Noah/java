package com.hniu.collection.gtm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GoodsTypeEditDialog extends JDialog {
    private JTextField txtId;
    private JTextField txtName;
    private JTextArea txtDescription;
    private JButton btnSave;
    private JButton btnCancel;
    private GoodsType goodsType;
    private boolean isEditMode;
    private boolean dataSaved = false;

    public GoodsTypeEditDialog(JFrame parent, String title, GoodsType goodsType, boolean isEditMode) {
        super(parent, title, true);
        this.goodsType = goodsType;
        this.isEditMode = isEditMode;

        initComponents();
        initData();
    }

    private void initComponents() {
        setSize(450, 400);
        setResizable(false);
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // 表单面板
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // 类型编号
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblId = new JLabel("类型编号：");
        lblId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        formPanel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        txtId = new JTextField();
        txtId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        txtId.setPreferredSize(new Dimension(250, 30));
        formPanel.add(txtId, gbc);

        // 类型名称
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblName = new JLabel("类型名称：");
        lblName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtName = new JTextField();
        txtName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        txtName.setPreferredSize(new Dimension(250, 30));
        formPanel.add(txtName, gbc);

        // 描述
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        JLabel lblDesc = new JLabel("描述：");
        lblDesc.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        formPanel.add(lblDesc, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtDescription = new JTextArea(5, 20);
        txtDescription.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        txtDescription.setLineWrap(true);
        txtDescription.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(txtDescription);
        scrollPane.setPreferredSize(new Dimension(250, 120));
        formPanel.add(scrollPane, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        btnSave = new JButton("保存");
        styleButton(btnSave, new Color(70, 130, 180));
        btnSave.addActionListener(this::saveData);

        btnCancel = new JButton("取消");
        styleButton(btnCancel, new Color(220, 80, 60));
        btnCancel.addActionListener(e -> dispose());

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private void initData() {
        if (isEditMode && goodsType != null) {
            txtId.setText(goodsType.getId());
            txtName.setText(goodsType.getName());
            txtDescription.setText(goodsType.getDescription());
        }
    }

    private void saveData(ActionEvent e) {
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "类型编号和名称不能为空！", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isEditMode) {
            goodsType = new GoodsType();
        }

        goodsType.setId(id);
        goodsType.setName(name);
        goodsType.setDescription(description);

        dataSaved = true;
        dispose();
    }

    public boolean isDataSaved() {
        return dataSaved;
    }

    public GoodsType getGoodsType() {
        return goodsType;
    }
}