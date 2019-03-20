package unionFind;

public class QuickFind {
	private int[] id;

	public QuickFind(int N) {
		id = new int[N];
		for(int i = 0; i < N; i++) 
			id[i] = i;
	}

	public boolean connected(int p, int q) {
		return(id[p] == id[q]);
	}

	public void union(int p, int q) {
		int pid = id[p];
		int qid = id[q];
		for(int i = 0; i <id.length; i++) {
			if(id[i] == pid) id[i] = qid;
		}
		//wrong way is to use id[p] in the if
		//then id[p] would be changed in the process 
		//and could not find out what need to be changed
		//in the rest of the array;

	}


}
