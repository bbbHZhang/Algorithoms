package unionFind;

public class QuickUnion {
	private int[] id;

	public QuickUnion(int N) {
		id = new int[N];
		for(int i = 0; i < N; i++) {
			id[i] = i;
		}
	}
	
	
	public int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];//change to weighted quick union with path compression
			i = id[i];
		}
		return i;
	}

	public boolean connected(int p, int q) {
//		int proot = p;
//		do {
//			proot = id[p];
//		}while(!(p == id[p]));
//
//		int qroot = q;
//		do {
//			qroot = id[q];
//		}while(!(q == id[q]));


		return(root(p) == root(q));
	}

	public void union(int p, int q) {
//		int proot = p;
//		do {
//			proot = id[p];
//		}while(!(p == id[p]));
//
//		int qroot = q;
//		do {
//			qroot = id[q];
//		}while(!(q == id[q]));
		
		id[root(p)] = root(q);
		
	}
}