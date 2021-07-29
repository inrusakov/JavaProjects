package CollectionTasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreeNodeImpl implements TreeNode {

    private TreeNode parent;
    private List<TreeNode> children = new ArrayList<>();
    private boolean exp;
    private Object data;

    public TreeNodeImpl() {
    }

    @Override
    public TreeNode getParent() {
        return this.parent;
    }

    @Override
    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    @Override
    public TreeNode getRoot() {
        TreeNode temp;
        if (this.getParent() != null) {
            temp = this;
            while (temp.getParent() != null) {
                temp = temp.getParent();
            }
        } else {
            return null;
        }
        return temp;
    }

    @Override
    public boolean isLeaf() {
        if (this.getChildCount() == 0)
            return true;
        else
            return false;
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public Iterator<TreeNode> getChildrenIterator() {
        return this.children.iterator();
    }

    @Override
    public void addChild(TreeNode child) {
        this.children.add(child);
        child.setParent(this);
    }

    @Override
    public boolean removeChild(TreeNode child) {
        if (child != null) {
            if (this.children.remove(child)) {
                child.setParent(null);
                this.children.remove(child);
                return true;
            } else
                return false;
        } else
            return false;
    }

    @Override
    public boolean isExpanded() {
        return this.exp;
    }

    @Override
    public void setExpanded(boolean expanded) {
        this.exp = expanded;
        for(TreeNode i: children)
            i.setExpanded(expanded);
    }

    @Override
    public Object getData() {
        return this.data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String getTreePath() {
        if (this.getParent() != null) {
            if (this.getData() == null) {
                return this.getParent().getTreePath() + "->" + "empty";
            } else {
                return this.getParent().getTreePath() + "->" + this.getData().toString();
            }
        }
        return this.getData().toString();
    }

    @Override
    public TreeNode findParent(Object data) {
        TreeNode inter = this;
        while (inter != null) {
            if (inter.getData() == null) {
                if (data == null) {
                    return inter;
                }
            } else if (inter.getData().equals(data)) {
                return inter;
            }
            inter = inter.getParent();
        }
        return null;
    }

    @Override
    public TreeNode findChild(Object data) {
        if (this.children != null) {
            Iterator<TreeNode> tmpIterator = getChildrenIterator();
            TreeNode prev, other;
            while (tmpIterator.hasNext()) {
                prev = tmpIterator.next();
                if (prev.getData() == null) {
                    if (data == null) {
                        return prev;
                    }
                } else if (prev.getData().equals(data)) {
                    return prev;
                }
                other = prev.findChild(data);
                if (other == null) {
                    continue;
                }
                return other;
            }
        }
        return null;
    }
}
