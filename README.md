# CS2114-Projcect2-Group14
Dungeon Explorer game using a linked chain

Reflection:
- What changed in LinkedChain: The original idea of how to iterate and add to the list was changed due to a beginning misconception with what the methods of the LinkedChain class would contain. We later found out of the addToEnd() method which made creating certain rooms easier.
- What changed from spec and why: We made changes to how the classes interacted to maintain simplicity. One example of this was to not go with our original ideas of inserting side rooms but to rather make a temperary Room object through makeSideRoom(). This keeps the main dungeons progression organized while allowing addition exploration.
- What unknows did we encounter: One unknown we encountered was how to manage movement between the main rooms and the side rooms. We pondered ideas of inserting a room into the main Chain as well as generating side rooms from the beginning and iterating over them when not chosen. We finally came to the conclusion to just make a temporary branching room which is then followed by the next iterated main room.
- What doesn't work yet: The game play has occasional line spacing inconsistencies and is overall very unbalanced. Main room weapons can barely attack side room encounters before breaking let alone the boss. This overly relies on luck for the player to get multiple high durability weapons before the boss without encountering many encounters.

Next Steps:
- What is left for the MVP: MVP is completely finished. Game works as intended and functions well.
- Stretch Goals: We are prioritizing testing and quality of life improvement for the MVP before moving on to stretch goals. An addition of varied combat mechanics and improved descriptions/presentation would greatly improve the enjoyment from the game over 1 or 2 stretch goals.
- Who Did What:
    - Victor: Git hub Repo, Dungeon,Encounter,Main, gameDataLoader.
    - Max: Junit tests for everything, Player, Room, gitignore.
- Additional Testing: Our next testing priorities are verifying weapon durability and swapping during gextended combat, boss damage calculations, inventory capacity limits. We also play to thoroughly test complete playthrough to identify issues that individual unit tests may not detect.
