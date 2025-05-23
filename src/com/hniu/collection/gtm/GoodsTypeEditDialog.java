package com.hniu.collection.gtm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 商品类型编辑对话框
 * 用于添加或修改商品类型信息
 */
public class GoodsTypeEditDialog extends JDialog {
    // 界面组件
    private JTextField txtId;          // 类型编号输入框
    private JTextField txtName;        // 类型名称输入框
    private JTextArea txtDescription;  // 描述文本域
    private JButton btnSave;           // 保存按钮
    private JButton btnCancel;         // 取消按钮

    // 数据对象
    private GoodsType goodsType;       // 当前编辑的商品类型对象
    private boolean isEditMode;        // 是否为编辑模式（true=编辑，false=添加）
    private boolean dataSaved = false; // 数据是否已保存标志

    /**
     * 构造函数
     * @param parent 父窗口
     * @param title 对话框标题
     * @param goodsType 要编辑的商品类型对象（新增时为null）
     * @param isEditMode 是否为编辑模式
     */
    public GoodsTypeEditDialog(JFrame parent, String title, GoodsType goodsType, boolean isEditMode) {
        super(parent, title, true);  // 调用父类构造方法，创建模态对话框
        this.goodsType = goodsType;
        this.isEditMode = isEditMode;

        initComponents();  // 初始化界面组件
        initData();        // 初始化数据
    }

    /**
     * 初始化界面组件
     */
    private void initComponents() {
        // 设置对话框基本属性
        setSize(450, 400);
        setResizable(false);
        setLocationRelativeTo(getParent());  // 居中显示
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));  // 设置背景色

        // ========== 主面板 ==========
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        mainPanel.setBackground(new Color(245, 245, 245));

        // ========== 表单面板 ==========
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));

        // 使用GridBagLayout布局管理器约束
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // 组件间距
        gbc.anchor = GridBagConstraints.WEST;     // 左对齐

        // 类型编号标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lblId = new JLabel("类型编号：");
        lblId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        formPanel.add(lblId, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;  // 水平填充
        gbc.weightx = 1.0;                         // 水平权重
        txtId = new JTextField();
        txtId.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        txtId.setPreferredSize(new Dimension(250, 30));
        formPanel.add(txtId, gbc);

        // 类型名称标签和输入框
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;  // 不填充
        JLabel lblName = new JLabel("类型名称：");
        lblName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        txtName = new JTextField();
        txtName.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        txtName.setPreferredSize(new Dimension(250, 30));
        formPanel.add(txtName, gbc);

        // 描述标签和文本域
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
        txtDescription.setLineWrap(true);      // 自动换行
        txtDescription.setWrapStyleWord(true);  // 按单词换行
        JScrollPane scrollPane = new JScrollPane(txtDescription);
        scrollPane.setPreferredSize(new Dimension(250, 120));
        formPanel.add(scrollPane, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // ========== 按钮面板 ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        buttonPanel.setBackground(new Color(245, 245, 245));

        // 保存按钮
        btnSave = new JButton("保存");
        styleButton(btnSave, new Color(70, 130, 180));  // 蓝色风格
        btnSave.addActionListener(this::saveData);      // 绑定保存事件

        // 取消按钮
        btnCancel = new JButton("取消");
        styleButton(btnCancel, new Color(220, 80, 60));  // 红色风格
        btnCancel.addActionListener(e -> dispose());    // 关闭对话框

        buttonPanel.add(btnSave);
        buttonPanel.add(btnCancel);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }

    /**
     * 设置按钮样式
     * @param button 要设置样式的按钮
     * @param bgColor 背景颜色
     */
    private void styleButton(JButton button, Color bgColor) {
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);  // 去除焦点边框
        button.setPreferredSize(new Dimension(100, 35));
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(bgColor.darker(), 1),  // 边框颜色加深
                BorderFactory.createEmptyBorder(5, 15, 5, 15)        // 内边距
        ));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));  // 手型光标
    }

    /**
     * 初始化数据
     * 如果是编辑模式，将商品类型数据显示在表单中
     */
    private void initData() {
        if (isEditMode && goodsType != null) {
            txtId.setText(goodsType.getId());
            txtName.setText(goodsType.getName());
            txtDescription.setText(goodsType.getDescription());
        }
    }

    /**
     * 保存数据
     * @param e 动作事件
     */
    private void saveData(ActionEvent e) {
        // 获取表单数据并去除前后空格
        String id = txtId.getText().trim();
        String name = txtName.getText().trim();
        String description = txtDescription.getText().trim();

        // 验证必填字段
        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "类型编号和名称不能为空！",
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 如果是新增模式，创建新对象
        if (!isEditMode) {
            goodsType = new GoodsType();
        }

        // 设置对象属性
        goodsType.setId(id);
        goodsType.setName(name);
        goodsType.setDescription(description);

        // 标记数据已保存并关闭对话框
        dataSaved = true;
        dispose();
    }

    /**
     * 获取数据是否已保存
     * @return true=数据已保存，false=未保存
     */
    public boolean isDataSaved() {
        return dataSaved;
    }

    /**
     * 获取商品类型对象
     * @return 当前编辑的商品类型对象
     */
    public GoodsType getGoodsType() {
        return goodsType;
    }
}