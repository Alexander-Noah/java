package com.hniu.collection.gtm;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品类型管理系统主界面
 */
public class GoodsTypeFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    // 存储商品类型的列表
    public static List<GoodsType> typeList = new ArrayList<>();

    // 界面组件
    private JTable table;              // 商品类型表格
    private DefaultTableModel tableModel; // 表格数据模型

    /**
     * 构造函数，初始化界面并加载示例数据
     */
    public GoodsTypeFrame() {
        initComponents();   // 初始化界面组件
        loadSampleData();   // 加载示例数据
    }

    /**
     * 初始化所有界面组件
     */
    private void initComponents() {
        // 设置窗口基本属性
        setTitle("商品类型管理系统");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // 主面板 - 使用BorderLayout布局
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        // ========== 标题面板 ==========
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel lblTitle = new JLabel("商品类型管理", JLabel.CENTER);
        lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 24));
        lblTitle.setForeground(new Color(70, 130, 180));
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        // ========== 按钮面板 ==========
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // 创建三个操作按钮
        JButton btnAdd = createStyledButton("添加", new Color(138, 43, 226)); // 紫色
        btnAdd.addActionListener(this::addType);  // 添加事件监听

        JButton btnEdit = createStyledButton("修改", new Color(138, 43, 226)); // 紫色
        btnEdit.addActionListener(this::editType);

        JButton btnDelete = createStyledButton("删除", new Color(123, 104, 238)); // 蓝紫色
        btnDelete.addActionListener(this::deleteType);

        // 将按钮添加到面板
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // ========== 表格面板 ==========
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));
        tablePanel.setBackground(Color.WHITE);

        // 创建表格模型，定义列名
        String[] columns = {"序号", "类型编号", "类型名称", "描述"};
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // 所有单元格不可编辑
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // 第一列显示为整数，其他列为字符串
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        // 创建表格并应用自定义样式
        table = new JTable(tableModel);
        customizeTable();

        // 将表格放入滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.PAGE_START);

        // 将表格面板添加到主面板
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    /**
     * 自定义表格样式
     */
    private void customizeTable() {
        // 设置表格字体和行高
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(220, 240, 255));

        // 设置所有列居中对齐
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 设置表头样式
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.BOLD, 14));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false); // 禁止列重排序

        // 表头文字居中对齐
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    /**
     * 创建样式化按钮
     * @param text 按钮文字
     * @param bgColor 背景颜色
     * @return 配置好的JButton
     */
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false); // 去除焦点边框
        button.setPreferredSize(new Dimension(100, 35));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 手型光标
        return button;
    }

    /**
     * 加载示例数据到表格
     */
    private void loadSampleData() {
        // 添加示例商品类型
        typeList.add(new GoodsType("001", "家电", "家用电器"));
        typeList.add(new GoodsType("002", "食品", "各类食品"));
        typeList.add(new GoodsType("003", "服装", "男女服装"));
        typeList.add(new GoodsType("004", "电子产品", "手机、电脑等"));
        typeList.add(new GoodsType("005", "家具", "居家用品"));

        refreshTable(); // 刷新表格显示
    }

    /**
     * 刷新表格数据
     */
    private void refreshTable() {
        tableModel.setRowCount(0); // 清空表格

        // 重新加载所有数据
        for (int i = 0; i < typeList.size(); i++) {
            GoodsType type = typeList.get(i);
            tableModel.addRow(new Object[]{i + 1, type.getId(), type.getName(), type.getDescription()});
        }
    }

    /**
     * 添加商品类型
     */
    private void addType(ActionEvent e) {
        // 打开添加对话框
        GoodsTypeEditDialog dialog = new GoodsTypeEditDialog(this, "添加商品类型", null, false);
        dialog.setVisible(true);

        // 如果用户确认保存，则添加到列表并刷新表格
        if (dialog.isDataSaved()) {
            typeList.add(dialog.getGoodsType());
            refreshTable();
        }
    }

    /**
     * 修改商品类型
     */
    private void editType(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("请先选择要修改的类型", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 获取选中的商品类型并打开编辑对话框
        GoodsType selectedType = typeList.get(selectedRow);
        GoodsTypeEditDialog dialog = new GoodsTypeEditDialog(this, "修改商品类型", selectedType, true);
        dialog.setVisible(true);

        // 刷新表格显示修改后的数据
        if (dialog.isDataSaved()) {
            refreshTable();
        }
    }

    /**
     * 删除商品类型
     */
    private void deleteType(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("请先选择要删除的类型", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 确认删除对话框
        int confirm = JOptionPane.showConfirmDialog(this,
                "确定要删除选中的类型吗？此操作不可恢复！",
                "确认删除",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        // 如果用户确认，则删除并刷新表格
        if (confirm == JOptionPane.YES_OPTION) {
            typeList.remove(selectedRow);
            refreshTable();
            showMessage("删除成功", "操作结果", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * 显示消息对话框
     * @param message 消息内容
     * @param title 对话框标题
     * @param messageType 消息类型
     */
    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    /**
     * 程序入口
     */
    public static void main(String[] args) {
        try {
            // 设置系统默认外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 在事件调度线程中创建并显示GUI
        SwingUtilities.invokeLater(() -> {
            GoodsTypeFrame frame = new GoodsTypeFrame();
            frame.setVisible(true);
        });
    }
}