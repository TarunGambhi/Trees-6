//https://leetcode.com/problems/serialize-and-deserialize-binary-tree
//TC : O(n)
//SC : O(h) height of the tree
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder result=new StringBuilder();
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.poll();
            if(node == null){
                result.append("#");
            }else{
                result.append(node.val);
                q.add(node.left);
                q.add(node.right);
            }
            result.append(",");
        }
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String [] arr = data.split(",");
        int idx=0;
        Queue<TreeNode> q = new LinkedList<>();
        if(arr[0].equals("#")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(arr[0]));
        q.add(root);
        idx++;
        while(idx<arr.length){
            TreeNode node = q.poll();
            if(!arr[idx].equals("#")){
                node.left = new TreeNode(Integer.parseInt(arr[idx]));
                q.add(node.left);
            }
            idx++;
            if(!arr[idx].equals("#")){
                node.right = new TreeNode(Integer.parseInt(arr[idx]));
                q.add(node.right);
            }
            idx++;
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
