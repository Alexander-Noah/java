package com.hniu.collection.gtm;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class GoodsTypeFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    public static List<GoodsType> typeList = new ArrayList<>();
    private JTable table;
    private DefaultTableModel tableModel;

    public GoodsTypeFrame() {
        initComponents();
        loadSampleData();
    }

    private void initComponents() {
        setTitle("商品类型管理系统");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 240, 240));
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        // 标题面板
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(240, 240, 240));
        JLabel lblTitle = new JLabel("商品类型管理", JLabel.CENTER);
        lblTitle.setFont(new Font("微软雅黑", Font.BOLD, 24));
        lblTitle.setForeground(new Color(70, 130, 180));
        titlePanel.add(lblTitle);
        mainPanel.add(titlePanel, BorderLayout.PAGE_START);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));

        // 修改按钮颜色
        JButton btnAdd = createStyledButton("添加", new Color(138, 43, 226)); // 紫色
        btnAdd.addActionListener(this::addType);

        JButton btnEdit = createStyledButton("修改", new Color(138, 43, 226)); // 紫色
        btnEdit.addActionListener(this::editType);

        JButton btnDelete = createStyledButton("删除", new Color(123, 104, 238)); // 蓝紫色
        btnDelete.addActionListener(this::deleteType);

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // 表格面板
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(0, 0, 0, 0),
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1)
        ));
        tablePanel.setBackground(Color.WHITE);
        // 表格模型
        String[] columns = {"序号", "类型编号", "类型名称", "描述"};
        tableModel = new DefaultTableModel(columns, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 0 ? Integer.class : String.class;
            }
        };

        table = new JTable(tableModel);
        customizeTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(buttonPanel, BorderLayout.PAGE_START);

        mainPanel.add(tablePanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void customizeTable() {
        table.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        table.setRowHeight(35);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(220, 240, 255));

        // 设置表格单元格居中对齐
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 表头样式设置
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("微软雅黑", Font.BOLD, 14));
        header.setBackground(new Color(70, 130, 180));
        header.setForeground(Color.BLACK);
        header.setReorderingAllowed(false);

        // 设置表头居中对齐
        ((DefaultTableCellRenderer)header.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
    }

    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(new Font("微软雅黑", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 35));
        button.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15)); // 移除边框线
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    private void loadSampleData() {
        typeList.add(new GoodsType("001", "家电", "家用电器"));
        typeList.add(new GoodsType("002", "食品", "各类食品"));
        typeList.add(new GoodsType("003", "服装", "男女服装"));
        typeList.add(new GoodsType("004", "电子产品", "手机、电脑等"));
        typeList.add(new GoodsType("005", "家具", "居家用品"));
        refreshTable();
    }

    private void refreshTable() {
        tableModel.setRowCount(0);
        for (int i = 0; i < typeList.size(); i++) {
            GoodsType type = typeList.get(i);
            tableModel.addRow(new Object[]{i + 1, type.getId(), type.getName(), type.getDescription()});
        }
    }

    private void addType(ActionEvent e) {
        GoodsTypeEditDialog dialog = new GoodsTypeEditDialog(this, "添加商品类型", null, false);
        dialog.setVisible(true);
        if (dialog.isDataSaved()) {
            typeList.add(dialog.getGoodsType());
            refreshTable();
        }
    }

    private void editType(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("请先选择要修改的类型", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        GoodsType selectedType = typeList.get(selectedRow);
        GoodsTypeEditDialog dialog = new GoodsTypeEditDialog(this, "修改商品类型", selectedType, true);
        dialog.setVisible(true);
        if (dialog.isDataSaved()) {
            refreshTable();
        }
    }

    private void deleteType(ActionEvent e) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            showMessage("请先选择要删除的类型", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "确定要删除选中的类型吗？此操作不可恢复！",
                "确认删除",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            typeList.remove(selectedRow);
            refreshTable();
            showMessage("删除成功", "操作结果", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showMessage(String message, String title, int messageType) {
        JOptionPane.showMessageDialog(this, message, title, messageType);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            GoodsTypeFrame frame = new GoodsTypeFrame();
            frame.setVisible(true);
        });
    }
}