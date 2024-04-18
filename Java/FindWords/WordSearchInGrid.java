import java.util.HashSet;
import java.util.Set;

public class WordSearchInGrid {
    private static final int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
    private static final int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };
    private static Set<String> foundWords = new HashSet<>();
    private static Trie dictionary = new Trie();

    public static Set<String> findWords(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return foundWords;

        int n = grid.length;
        int m = grid[0].length;
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dfs(grid, i, j, "", visited);
            }
        }

        return foundWords;
    }

    private static void dfs(char[][] grid, int x, int y, String currentWord, boolean[][] visited) {
        if (!dictionary.startsWith(currentWord))
            return;

        if (dictionary.contains(currentWord)) {
            foundWords.add(currentWord);
        }

        visited[x][y] = true;
        for (int i = 0; i < 8; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < grid.length && ny >= 0 && ny < grid[0].length && !visited[nx][ny]) {
                dfs(grid, nx, ny, currentWord + grid[nx][ny], visited);
            }
        }
        visited[x][y] = false;
    }

    public static void main(String[] args) {
        char[][] grid = {
                { 'L', 'M', 'U', 'D' },
                { 'D', 'R', 'R', 'E' },
                { 'A', 'E', 'H', 'E' },
                { 'S', 'D', 'T', 'S' }
        };

        // Define a list of dictionary words
        String[] dictionaryWords = {
                "MUD", "RED", "REDHEAD", "DREADED", "STEERED", "DREAM", "DARE", "READ", "DEAR", "SEARED"
        };

        // Insert words into the Trie from the predefined array
        for (String word : dictionaryWords) {
            dictionary.insert(word);
        }

        Set<String> result = findWords(grid);
        System.out.println("Found words: " + result);
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            if (node.children[c - 'A'] == null) {
                node.children[c - 'A'] = new TrieNode();
            }
            node = node.children[c - 'A'];
        }
        node.isWord = true;
    }

    public boolean contains(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode node = searchPrefix(prefix);
        return node != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            if (node.children[c - 'A'] == null) {
                return null;
            }
            node = node.children[c - 'A'];
        }
        return node;
    }

    private class TrieNode {
        private TrieNode[] children = new TrieNode[26];
        private boolean isWord = false;
    }
}
