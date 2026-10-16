public class Dungeon {
    private final LinkedChain<Encounter> encounterCatalog;
    private final LinkedChain<Room> rooms;
    private int currentMainRoomIndex;
    private Room currentRoom;
    private Room sideRoom;

    public Dungeon() {
        encounterCatalog = new LinkedChain<>();
        rooms = new LinkedChain<>();
        currentMainRoomIndex = 0;
        currentRoom = null;
        sideRoom = null;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
    
    public int getRoomCount()
    {
        return rooms.size();
    }

    public void makeDungeon() {
        encounterCatalog.clear();
        rooms.clear();
        currentMainRoomIndex = 0;
        sideRoom = null;

        GameDataLoader loader = new GameDataLoader();
        String[] firstRoomNameAndDescription = loader.mainRoomsNamesAndDescriptions();
        Room firstRoom = new Room(loader.getRandomMainItem(), null, firstRoomNameAndDescription[0], Room.RoomType.MAIN, firstRoomNameAndDescription[1], true);
        rooms.addToEnd(firstRoom);

        for (int i = 0; i < 5; i++){

            Item loot = null;
            Encounter encounter = null;
            Room.RoomType type = Room.RoomType.MAIN;
            boolean sideRoomAvailable = true;
            String[] roomNameAndDescription = loader.mainRoomsNamesAndDescriptions();

            if(Math.random()<.7){
                loot = loader.getRandomMainItem();
            }
            if(Math.random()<.3){
                encounter = loader.getRandomMainEncounter();
            }
            if (Math.random()<.2){
                sideRoomAvailable = false;
            }
            


            Room room = new Room(loot, encounter, roomNameAndDescription[0], type, roomNameAndDescription[1], sideRoomAvailable);
            rooms.addToEnd(room);
        }
        makeBossRoom();
        currentRoom = rooms.get(0);
    }
    public void moveToNextRoom() {
        if (canMoveToNextRoom()) {
            currentMainRoomIndex++;
            currentRoom = rooms.get(currentMainRoomIndex);
            recordCurrentEncounter();
        }
    }
    public boolean canMoveToNextRoom() {
        return currentMainRoomIndex < rooms.size() - 1;
    }
    public void moveToSideRoom(){
        currentRoom = makeSideRoom();
        recordCurrentEncounter();
    }
    public Room makeSideRoom() {
        if (currentRoom.isSideRoomAvailable() && sideRoom == null) {
            GameDataLoader loader = new GameDataLoader();
            Item loot = null;
            Encounter encounter = null;
            Room.RoomType type = Room.RoomType.SIDE;
            boolean sideRoomAvailable = false;
            String[] roomNameAndDescription = loader.sideRoomsNamesAndDescriptions();

            if(Math.random()<.75){
                loot = loader.getRandomSideItem();
            }
            if(Math.random()<.4){
                encounter = loader.getRandomSideEncounter();
            }

            sideRoom = new Room(loot, encounter, roomNameAndDescription[0], type, roomNameAndDescription[1], sideRoomAvailable);
        }
        return sideRoom;
    }
    public void makeBossRoom() {
        GameDataLoader loader = new GameDataLoader();
        String[] roomNameAndDescription = loader.mainRoomsNamesAndDescriptions();
        Encounter encounter = loader.getRandomBossEncounter();
        encounter.setArmor();
        Room bossRoom = new Room(null, encounter, roomNameAndDescription[0], Room.RoomType.BOSS, roomNameAndDescription[1], false);
        rooms.addToEnd(bossRoom);
    }
    public void leaveSideRoom() {
        sideRoom = null;
        moveToNextRoom();
    }
    private void recordCurrentEncounter() {
        Encounter enemy = currentRoom.getEncounter();

        if (enemy != null) {
            encounterCatalog.add(enemy);
        }
    }
    public int getCatalogSize() {
        return encounterCatalog.size();
    }

    public Encounter getCatalogEncounter(int index) {
        return encounterCatalog.get(index);
    }
}
