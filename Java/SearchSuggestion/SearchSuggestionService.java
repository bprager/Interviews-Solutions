import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isEndOfWord = false; // Marks the end of a word
}

class Trie {
    private TrieNode root = new TrieNode();

    // Inserts a word into the trie
    public void insert(String word) {
        TrieNode current = root;
        for (char l : word.toCharArray()) {
            current = current.children.computeIfAbsent(l, c -> new TrieNode());
        }
        current.isEndOfWord = true;
    }

    // Searches for all completions of a given prefix
    public List<String> searchSuggestions(String prefix) {
        List<String> suggestions = new ArrayList<>();
        TrieNode current = root;
        for (char l : prefix.toCharArray()) {
            TrieNode node = current.children.get(l);
            if (node == null) {
                return suggestions; // No suggestions found
            }
            current = node;
        }
        findAllWords(current, suggestions, prefix);
        return suggestions;
    }

    // Helper method to recursively find all words in the trie that begin with the
    // given prefix
    private void findAllWords(TrieNode node, List<String> suggestions, String prefix) {
        if (node.isEndOfWord) {
            suggestions.add(prefix);
        }
        for (Character c : node.children.keySet()) {
            findAllWords(node.children.get(c), suggestions, prefix + c);
        }
    }
}

public class SearchSuggestionService {

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Here you would read your text file and for each line do:
        // trie.insert(line.trim().toLowerCase());

        // Example insertion (assuming file reading is done)
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apricot");
        trie.insert("banana");

        System.out.println("Suggestions for 'ap': " + trie.searchSuggestions("ap"));
        // This should print: Suggestions for 'ap': [apple, app, apricot]
    }
}
