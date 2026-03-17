class Trie { // 根节点啥都不存
    private Trie[] nextNode;
    private int count;
    private int prefix;

    public Trie() {
        nextNode = new Trie[26];
        count = 0;
        prefix = 0;
    }
    
    public void insert(String word) {
        int len = word.length();
        Trie root = this;
        for (int i = 0; i < len; ++i){
            char c = word.charAt(i);
            if (root.nextNode[c - 'a'] == null){
                root.nextNode[c - 'a'] = new Trie();
            }
            root = root.nextNode[c - 'a'];
            root.prefix++;
        }
        root.count++;
    }
    
    public boolean search(String word) {
        int len = word.length();
        Trie root = this;
        for (int i = 0; i < len; ++i){
            char c = word.charAt(i);
            if (root.nextNode[c - 'a'] == null || root.nextNode[c - 'a'].prefix == 0)
                return false;
            root = root.nextNode[c - 'a'];
        }
        // 检查count
        return root.count != 0;
    }
    
    public boolean startsWith(String prefix) {
        int len = prefix.length();
        Trie root = this;
        for (int i = 0; i < len; ++i){
            char c = prefix.charAt(i);
            if (root.nextNode[c - 'a'] == null || root.nextNode[c - 'a'].prefix == 0)
                return false;
            root = root.nextNode[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
