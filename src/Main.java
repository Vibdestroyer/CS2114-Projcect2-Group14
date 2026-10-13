public class Main {
    public static void main(String[] args) {

        GameDataLoader loader = new GameDataLoader();

        LinkedChain<Item> items =
            loader.loadItems("data/main_room_items.txt");

        System.out.println("Items loaded: " + items.size());
        System.out.println(items.get(0).getName());
    }
}