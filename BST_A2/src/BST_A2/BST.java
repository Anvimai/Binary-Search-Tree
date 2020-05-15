package BST_A2;


public class BST implements BST_Interface {
  private static BST_Node parent;


public BST_Node root;


  int size;
  
  public BST(){ size=0; root=null; }
  
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

public boolean insert(String s) {
	
	BST_Node nNode = new BST_Node(s);

	
	if(root == null) {root = nNode; size++; return true;}
	if(contains(s)) {return false;}
	
	insertRecurs(nNode, root);
	
	size++;
	
	return true;
}

public boolean remove(String s) {
	
	  if(!contains(s)) {return false;}

	
	  BST_Node forRemoval = toBeRemoved(root,s);
	  BST_Node n; 
	
	//removing root if no children
	if(size==1) {root = null; size = 0; return true;}
	//removing leaves with no children
	if(forRemoval.getLeft() == null && forRemoval.getRight() == null && parent != null) {
		if(forRemoval.getData().compareTo(parent.getData()) > 0) {parent.right = null; size--; return true;
		}else {parent.left = null; size--; return true;}
		}
		
	if(forRemoval.getLeft()!=null) {
	n = findMaxInMin(forRemoval.getLeft());
	} else{n = findMaxInMin(forRemoval.getRight());
	}

	//if forRemove is the root and it has children
	if(parent == null) {
		//if forRemoval has left child and no parent then new root = left child
		if(forRemoval.left!=null) {
		root = forRemoval.left;
		//if forRemoval has no left child and no parent then new root = right child size decrease by 1 and return true 
		}else {root = forRemoval.right; size--; return true;}
		
		//if forRemoval has left child, no parent, and has right child then new root.right = forRemoval.right size decrease by 1 and return true
		if(forRemoval.right != null) {
		root.right = forRemoval.right;}
	size--; return true;
	
	//if parent != null
	}else{
		
		//if forRemoval is left child
		if(forRemoval.getData().compareTo(parent.getData())>0) {
			//if forRemoval has left child
		if(forRemoval.left!=null) {	
		parent.right = forRemoval.left;
		//if forRemoval has no left child
		}else {parent.right = forRemoval.right; size--; return true;}
		//if forRemoval has right child
		if(forRemoval.right!=null) {
		n.right = forRemoval.right;} 
		size--; return true;
		}else {	//if forRemoval has left child
			if(forRemoval.left!=null) {	
				parent.left = forRemoval.left;
				//if forRemoval has no left child
				}else {parent.left = forRemoval.right; size--; return true;}
				//if forRemoval has right child
				if(forRemoval.right!=null) {
				n.right = forRemoval.right;} 
				size--; return true;}
	}
	
	
	
	

	
}

public String findMin() {
	
	if(size() == 0) {return null;}
	
	BST_Node n = root;
	while(n.getLeft()!= null) {n = n.getLeft();}
	
	
	
	return n.getData();
}

public String findMax() {
	if(size() == 0) {return null;}
	
	BST_Node n = root;
	while(n.getRight()!= null) {n = n.getRight();}
	
	
	return n.getData();
}

public boolean empty() {
	
	if(root == null) {return true;}
	return false;
}

public boolean contains(String s) {
	
	return containsRecurs(root,s);
}

public int size() {
	return size;
}

public int height() {
	if(size()==0) {return -1;}
	
	return heightRecurs(root);
	
}

  //--------------------------------------------------
  //
  // of course, fill in the methods implementations
  // for the interface
  //
  //--------------------------------------------------

public boolean insertRecurs(BST_Node nNode, BST_Node n) {
	
	if(n.getData().compareTo(nNode.getData()) < 0) {
		
		if(n.getRight() == null) {n.right = nNode; return true;}else {insertRecurs(nNode, n.right);}
	}
	
	if(n.getData().compareTo(nNode.getData()) > 0) {
		
		if(n.getLeft() == null) {n.left = nNode; return true;}else {insertRecurs(nNode,n.left);}
	}
	
	return false; 
}

static boolean containsRecurs(BST_Node root, String s){
	
	
if(root!=null){
	if(root.getData().compareTo(s) == 0) {return true;}else {
		if(root.getData().compareTo(s) > 0) {
			return containsRecurs(root.getLeft(),s);}else {
				return containsRecurs(root.getRight(),s);
				}
		}
}

return false; 
}

static BST_Node toBeRemoved(BST_Node root, String s){
	
	
if(root!=null){
	if(root.getData().compareTo(s) == 0) {return root;}else {
		if(root.getData().compareTo(s) > 0) {
			parent = root;
			return toBeRemoved(root.getLeft(),s);}else {
				parent = root;
				return toBeRemoved(root.getRight(),s);
				}
		}
}

return null; 
}

public BST_Node findMaxInMin(BST_Node tempRoot) {
	if(size() == 0) {return null;}
	
	if(tempRoot.getRight() == null) {return tempRoot;}
	
	while(tempRoot.getRight()!= null) {tempRoot = tempRoot.getRight();}
	
	
	return tempRoot;
}

public int heightRecurs(BST_Node n) {
	if(n == null) {return -1;}
	
	
	int leftHeight = heightRecurs(n.left);
	int rightHeight = heightRecurs(n.right);
	
	if(leftHeight > rightHeight) {return leftHeight + 1;}else {return rightHeight + 1; }
	
}


}