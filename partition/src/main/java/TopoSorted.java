/**
 * @author: miaoxing
 * DATE:    2017/7/4
 */
public class TopoSorted {

    class Vertex {
        char label;
        Vertex(char lab) {
            label = lab;
        }
    }

    private final int MAX_VERTS = 20;
    private Vertex vertexArray[]; //存储顶点的数组
    private int adjMat[][]; //存储是否有边界的矩阵数组, 0表示没有边界，1表示有边界
    private int nVerts; //顶点个数
    private char sortedArray[]; //存储排过序的数据的数组

    public TopoSorted() {
        vertexArray = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }
        }
        sortedArray = new char[MAX_VERTS];
    }

    public void addVertex(char lab) {
        vertexArray[nVerts++] = new Vertex(lab);
    }

    //有向图中，邻接矩阵中只有一项
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
    }

    public void displayVertex(int v) {
        System.out.print(vertexArray[v].label);
    }

    /*
     * 拓扑排序
     */
    public void topo() {
        int orig_nVerts = nVerts; //remember how many verts
        while (nVerts > 0) {
            //get a vertex with no successors or -1
            int currentVertex = noSuccessors();
            if (currentVertex == -1) {
                System.out.println("ERROR: Graph has cycles!");
                return;
            }

            //insert vertex label in sortedArray (start at end)
            sortedArray[nVerts - 1] = vertexArray[currentVertex].label;
            deleteVertex(currentVertex);
        }
        System.out.println("Topologically sorted order:");
        for (int i = 0; i < orig_nVerts; i++) {
            System.out.print(sortedArray[i]);
        }
        System.out.println("");
    }

    //return vertex with no successors
    private int noSuccessors() {
        boolean isEdge;
        for (int row = 0; row < nVerts; row++) {
            isEdge = false;
            for (int col = 0; col < nVerts; col++) {
                if (adjMat[row][col] > 0) {
                    isEdge = true;
                    break;
                }
            }
            if (!isEdge) {
                return row;
            }
        }
        return -1;
    }

    private void deleteVertex(int delVertex) {
        if (delVertex != nVerts - 1) {
            for (int i = delVertex; i < nVerts - 1; i++) { //delete from vertexArray
                vertexArray[i] = vertexArray[i + 1];
            }

            for (int row = delVertex; row < nVerts - 1; row++) {//delete row from adjMat
                moveRowUp(row, nVerts);
            }

            for (int col = delVertex; col < nVerts - 1; col++) {//delete column from adjMat
                moveColLeft(col, nVerts - 1);
            }
        }
        nVerts--;
    }

    private void moveRowUp(int row, int length) {
        for (int col = 0; col < length; col++) {
            adjMat[row][col] = adjMat[row + 1][col];
        }
    }

    private void moveColLeft(int col, int length) {
        for (int row = 0; row < length; row++) {
            adjMat[row][col] = adjMat[row][col + 1];
        }
    }

    public static void main(String[] args) {
//        TopoSorted topoSorted = new TopoSorted();
//
//        topoSorted.addVertex('A');
//        topoSorted.addVertex('B');
//        topoSorted.addVertex('C');
//        topoSorted.addVertex('D');
//        topoSorted.addVertex('E');
//        topoSorted.addVertex('F');
//        topoSorted.addVertex('G');
//        topoSorted.addVertex('H');
//
//        topoSorted.addEdge(0, 3);
//        topoSorted.addEdge(0, 4);
//        topoSorted.addEdge(1, 4);
//        topoSorted.addEdge(2, 5);
//        topoSorted.addEdge(3, 6);
//        topoSorted.addEdge(4, 6);
//        topoSorted.addEdge(5, 7);
//        topoSorted.addEdge(6, 7);
//
//        topoSorted.topo();

        String a = "asd";
        String replace = a.replace("s", "f");
        System.out.println(a);
        System.out.println(replace);
    }
}
