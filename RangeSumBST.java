//https://leetcode.com/problems/range-sum-of-bst
//TC :O(n)
//SC :o(h)
//int dfs
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        return helper(root,low,high);
    }
    private int helper(TreeNode root, int low, int high){
        
        if(root == null) return 0;
        int total = 0;
        total += helper(root.left,low,high);
        total += helper(root.right,low,high);
        if(root.val >= low && root.val <= high){
            total += root.val;
        }
        return total;
    }
}

//void dfs
class Solution {
    int result;
    public int rangeSumBST(TreeNode root, int low, int high) {
        result =0;
        helper(root,low,high);
        return result;
    }
    private void helper(TreeNode root, int low, int high){
        
        if(root == null) return ;
        if(root.val >= low)
            helper(root.left,low,high);
        if(root.val < high)
            helper(root.right,low,high);
        if(root.val >= low && root.val <= high){
            result += root.val;
        }
    }
  
  //BST level order
  
  class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int result = 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node.val >= low && node.val<=high){
                result += node.val;
            }
            if(node.val >= low && node.left != null)
                q.add(node.left);
            if(node.val < high && node.right != null)
                q.add(node.right);
        }
        return result;
    }
}
  //BST inorder
  class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int result = 0;
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.isEmpty()){
            while(root != null){
                st.push(root);
                if(root.val > low)
                    root = root.left;
                else
                    root = null;
            }
            root = st.pop();
            if(root.val >= low && root.val<=high){
                result += root.val;
            }
            if(root.val < high)
                root = root.right;
            else
                root = null;
        }
        return result;
    }
   
}
  
  // BST pre order
  class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        int result = 0;
        Stack<TreeNode> st = new Stack<>();
        while(root != null || !st.isEmpty()){
            if(root != null){
                if(root.val < high)
                    st.push(root.right);
                if(root.val > low)
                    st.push(root.left);
                if(root.val >= low && root.val <= high){
                    result += root.val;
                }
            }
            root = st.pop();
        }
        return result;
    }
   
}
//BST post order
    class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if(root == null) return 0;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        int result = 0;
        TreeNode prev = null;
        while(!st.isEmpty()){
            TreeNode curr = st.peek();
            if(prev == null || prev.left == curr || prev.right == curr){
                if(curr.left != null && curr.val > low)
                    st.push(curr.left);
                else if(curr.right != null && curr.val < high)
                    st.push(curr.right);
                else{
                    st.pop();
                    if(curr.val >= low && curr.val <= high)
                        result += curr.val;
                } 
            }else if(curr.left == prev){
                if(curr.right != null && curr.val < high)
                    st.push(curr.right);
                else{
                    st.pop();
                    if(curr.val >= low && curr.val <= high)
                        result += curr.val;
                } 
            }else if(curr.right == prev){
                st.pop();
                if(curr.val >= low && curr.val <= high)
                    result += curr.val;
            }
            prev = curr;
        }
        return result;
    }
   
}
