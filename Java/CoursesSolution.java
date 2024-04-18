/* This problem can be seen as detecting a cycle in a directed graph. If there's a cycle, that means we have a circular dependency among the courses, and we can't finish them all. 
 * If there isn't, that means we can take the courses in a topological order.
 * The common algorithm for this task is the Depth-First Search (DFS).
 * 
 * Here's how we can implement the solution:
 * Create an adjacency list representation of the graph.  Use Depth-First Search (DFS) to detect a cycle.
 * 
 * In this solution, we use a recursive DFS approach to traverse the graph, and we maintain a visited array to track the status of each node. 
 * If we encounter a node that's currently being visited, then there's a cycle.
 */

import java.util.ArrayList
import java.util.List;

public class Courses {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Create an adjacency list representation of the graph
        List<Integer>[] graph = new ArrayList[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] prerequisite : prerequisites) {
            graph[prerequisite[1]].add(prerequisite[0]);
        }

        // States: 0 = unvisited, 1 = visiting, 2 = visited
        int[] visited = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            if (visited[i] == 0 && !dfs(graph, visited, i)) {
                return false; // If there's a cycle, return false
            }
        }

        return true; // No cycle found
    }

    private boolean dfs(List<Integer>[] graph, int[] visited, int course) {
        // If the node is being visited, it means we have a cycle
        if (visited[course] == 1) {
            return false;
        }

        // If the node has been visited, just return true
        if (visited[course] == 2) {
            return true;
        }

        // Mark the node as being visited
        visited[course] = 1;

        for (int nextCourse : graph[course]) {
            if (!dfs(graph, visited, nextCourse)) {
                return false; // Cycle detected
            }
        }

        // Mark the node as visited
        visited[course] = 2;

        return true;
    }
}
