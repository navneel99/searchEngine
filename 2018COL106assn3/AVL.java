
class AVLNode{
    int key, height;
    Position p;
    AVLNode left, right;

    public AVLNode(Position pos ){
        int wi = pos.i;
        this.p = pos;
        this.key = wi;
        this.height = 1;
    }
}

    
    public class AVL{
        AVLNode root;
    
        int height(AVLNode N) { 
            if (N == null) 
                return 0; 
            return N.height; 
        } 
      
        // A utility function to get maximum of two integers 
        int max(int a, int b) { 
            return (a > b) ? a : b; 
        } 
      
        // A utility function to right rotate subtree rooted with y 
        // See the diagram given above. 
        AVLNode rightRotate(AVLNode y) { 
            AVLNode x = y.left; 
            AVLNode T2 = x.right; 
      
            // Perform rotation 
            x.right = y; 
            y.left = T2; 
      
            // Update heights 
            y.height = max(height(y.left), height(y.right)) + 1; 
            x.height = max(height(x.left), height(x.right)) + 1; 
      
            // Return new root 
            return x; 
        } 
      
        // A utility function to left rotate subtree rooted with x 
        // See the diagram given above. 
        AVLNode leftRotate(AVLNode x) { 
            AVLNode y = x.right; 
            AVLNode T2 = y.left; 
      
            // Perform rotation 
            y.left = x; 
            x.right = T2; 
      
            //  Update heights 
            x.height = max(height(x.left), height(x.right)) + 1; 
            y.height = max(height(y.left), height(y.right)) + 1; 
      
            // Return new root 
            return y; 
        } 
      
        // Get Balance factor of AVLNode N 
        int getBalance(AVLNode N) { 
            if (N == null) 
                return 0; 
      
            return height(N.left) - height(N.right); 
        } 
      
        AVLNode insert(AVLNode node, Position pos) { 
      
            // 1.  Perform the normal BST insertion 
            if (node == null) 
                return (new AVLNode(pos)); 
      
            if (pos.i <= node.key) 
                node.left = insert(node.left, pos); 
            else if (pos.i > node.key) 
                node.right = insert(node.right, pos); 
            //else // Duplicate keys not allowed 
            //    return node; 
      
            /* 2. Update height of this ancestor node */
            node.height = 1 + max(height(node.left), 
                                  height(node.right)); 
      
            /* 3. Get the balance factor of this ancestor 
                  node to check whether this node became 
                  unbalanced */
            int balance = getBalance(node); 
      
            // If this node becomes unbalanced, then there 
            // are 4 cases Left Left Case 
            if (balance > 1 && pos.i < node.left.key) 
                return rightRotate(node); 
      
            // Right Right Case 
            if (balance < -1 && pos.i > node.right.key) 
                return leftRotate(node); 
      
            // Left Right Case 
            if (balance > 1 && pos.i > node.left.key) { 
                node.left = leftRotate(node.left); 
                return rightRotate(node); 
            } 
      
            // Right Left Case 
            if (balance < -1 && pos.i < node.right.key) { 
                node.right = rightRotate(node.right); 
                return leftRotate(node); 
            } 
      
            /* return the (unchanged) node pointer */
            return node; 
        } 
      
        // A utility function to print preorder traversal 
        // of the tree. 
        // The function also prints height of every node 
        void preOrder(AVLNode node) { 
            if (node != null) { 
                System.out.print(node.p.page.page  + " "); 
                preOrder(node.left); 
                preOrder(node.right); 
            }
        }
        
        boolean doesPositionExist(PageEntry p, int index, AVLNode node){
            if (node != null){
                if (node.key >= index){
                    if (node.key == index && node.p.page.page.equals(p.page)){
                        return true;
                    } else{
                        return doesPositionExist(p, index, node.left);
                    }
                } else {
                    return doesPositionExist(p, index, node.right);
                }
            }
            return false;
        }
  
   public static void main(String[] args) { 
        AVL tree = new AVL(); 
   }
    }
