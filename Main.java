import java.util.*;

import javax.sound.midi.Soundbank;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class HtmlTree extends JFrame {
    JTree tree;

    public HtmlTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("html");
        DefaultMutableTreeNode head = new DefaultMutableTreeNode("head");
        DefaultMutableTreeNode body = new DefaultMutableTreeNode("body");
        DefaultMutableTreeNode meta = new DefaultMutableTreeNode("meta");
        DefaultMutableTreeNode title = new DefaultMutableTreeNode("title");
        DefaultMutableTreeNode ul = new DefaultMutableTreeNode("ul");
        DefaultMutableTreeNode li = new DefaultMutableTreeNode("li");
        DefaultMutableTreeNode li1 = new DefaultMutableTreeNode("li");
        DefaultMutableTreeNode h1 = new DefaultMutableTreeNode("h1");
        DefaultMutableTreeNode h2 = new DefaultMutableTreeNode("h2");
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("a");

        tree = new JTree(root);
        root.add(head);
        root.add(body);

        head.add(meta);
        head.add(title);

        body.add(ul);
        body.add(h1);
        body.add(h2);

        ul.add(li);
        ul.add(li1);

        h2.add(a);

        add(tree);

        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) tree.getModel().getRoot();
        System.out.println("Root node: " + rootNode.getUserObject());

        System.out.println("Parent nodes: " + "[" + head.getParent() + "]" + "[" + meta.getParent() + "]" + "["
                + ul.getParent() + "]" + "[" + li.getParent() + "]" + "[" + a.getParent() + "]");

        List<DefaultMutableTreeNode> targetNodes = new ArrayList<>();
        targetNodes.add(head);
        targetNodes.add(meta);
        targetNodes.add(ul);
        targetNodes.add(li1);
        for (DefaultMutableTreeNode targetNode : targetNodes) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) targetNode.getParent();
            if (parent != null) {
                for (int i = 0; i < parent.getChildCount(); i++) {
                    DefaultMutableTreeNode sibling = (DefaultMutableTreeNode) parent.getChildAt(i);
                    if (!sibling.equals(targetNode)) {
                        System.out.println("Sibling: " + "[" + targetNode + "]" + "[" + sibling.getUserObject() + "]");
                    }
                }
            }
        }
        System.out.println("one-level Subtree: " + head.getParent() + "-" + "[" + head.getNextSibling() + ","
                + body.getPreviousSibling() + "]" + " " + body.getPreviousSibling() + "-" + "[" + meta.getNextSibling()
                + "," + title.getPreviousSibling() + "]" + " " + h1.getParent() + "-" + "[" + h1.getPreviousSibling()
                + "," + ul.getNextSibling() + "," + h1.getNextSibling() + "]" + " " + li.getParent() + "-" + "["
                + li.getNextSibling() + "," + li1.getPreviousSibling() + "]" + " " + a.getParent() + "-" + "[" + a
                + "]");

        System.out.println("Nodes per level");
        System.out.println("Root Level: " + root.getLevel());
        System.out.println("body, head Level: " + head.getLevel());
        System.out.println("meta, title, ul, h1, h2 Level: " + h2.getLevel());
        System.out.println("li, li, a Level: " + a.getLevel());

        System.out.println("Depth: " + root.getDepth());

        System.out.println("Degree of each one-level subtree:");
        System.out.println(root + " - " + root.getChildCount());
        System.out.println(head + " - " + head.getChildCount());
        System.out.println(body + " - " + body.getChildCount());
        System.out.println(ul + " - " + ul.getChildCount());
        System.out.println(h2 + " - " + h2.getChildCount());

        System.out.println("List of nodes based on breadth-first:");
        printBreadthFirst(root);

        System.out.println("List of nodes based on preorder:");
        printPreorder(root);

        System.out.println("List of nodes based on postorder:");
        printPostorder(root);

        this.setTitle("Task Performance");
        this.setSize(300, 300);
        this.setVisible(true);

    }

    public void printBreadthFirst(DefaultMutableTreeNode root) {
        Queue<DefaultMutableTreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            DefaultMutableTreeNode node = queue.poll();
            System.out.println(node.getUserObject());

            for (int i = 0; i < node.getChildCount(); i++) {
                DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
                queue.add(child);
            }
        }
    }

    public void printPreorder(DefaultMutableTreeNode node) {
        if (node == null)
            return;

        System.out.println(node.getUserObject());

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            printPreorder(child);
        }
    }

    public void printPostorder(DefaultMutableTreeNode node) {
        if (node == null)
            return;

        for (int i = 0; i < node.getChildCount(); i++) {
            DefaultMutableTreeNode child = (DefaultMutableTreeNode) node.getChildAt(i);
            printPostorder(child);
        }

        System.out.println(node.getUserObject());
    }

    public static void main(String[] args) {
        new HtmlTree();
    }
}