# My Personal Project

## Proposal
Food wastage has always been a challenging problem in the food industry. 
On the one hand, it is costly for businesses to transport and dispose leftovers. 
On the other hand, food waste leads to greenhouse gas emissions, which is a major contributor to climate change. 
Therefore, I plan to build an application to help fight food waste. 
This application allows restaurants/hotels to sell unsold food and surplus buffet to customers at a largely reduced price. 
It lets the merchants enter information about their offerings. 
Possible features include showing the customers the locations, the time, the prices, and the food varieties.

## User Stories

- As a user, I want to be able to add an offering to a given restaurant
- As a user, I want to be able to remove an offering from a given restaurant
- As a user, I want to be able to add a restaurant to the list of my restaurants
- As a user, I want to be able to remove a restaurant from the list of my restaurants (including all its offerings)
- As a user, I want to be able to search by restaurant name and view the list of offerings in this restaurant
- As a user, I want to be able to save my listings and restaurants to file (if I so choose)
- As a user, I want to be able to load my listings and restaurants from file (if I so choose)

## Phase 4: Task 2
/Users/violetyang/Library/Java/JavaVirtualMachines/corretto-11.0.17/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA CE.app/Contents/lib/idea_rt.jar=52259:/Applications/IntelliJ IDEA CE.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/violetyang/IdeaProjects/project_x6f1u/out/production/Project-Starter:/Users/violetyang/IdeaProjects/project_x6f1u/lib/json-20210307.jar ui.Main
Fri Apr 07 22:51:57 PDT 2023
Added restaurant: Hyatt

Fri Apr 07 22:51:57 PDT 2023
Added Offering: 1  |  10  |  14:00 - 15:00  |  2nd floor, 3337 W 45th Ave  |  Chinese

Fri Apr 07 22:51:57 PDT 2023
Added Offering: 2  |  20  |  20:00 - 21:00  |  3nd floor, 3337 W 45th Ave  |  Thai

Fri Apr 07 22:51:57 PDT 2023
Added restaurant: Hilton

Fri Apr 07 22:51:57 PDT 2023
Added Offering: 1  |  25  |  14:00 - 15:00  |  2nd floor, 44 W 10th Ave  |  Mexican

Fri Apr 07 22:51:57 PDT 2023
Added Offering: 2  |  20  |  20:00 - 21:00  |  1nd floor, 44 W 10th Ave  |  Indian

Fri Apr 07 22:52:05 PDT 2023
Added restaurant: violet

Fri Apr 07 22:52:29 PDT 2023
Added Offering: 4  |  4  |  4  |  4  |  4

Fri Apr 07 22:52:33 PDT 2023
Removed Option: 4

Fri Apr 07 22:52:53 PDT 2023
Removed restaurant: violet

Fri Apr 07 22:52:56 PDT 2023
Restaurant saved


Process finished with exit code 0

## Phase 4: Task 3
Given additional time to work on this project, I would restructure the UI component into more classes, 
specifically the OfferingGUI and ListingAppGUI classes, to better organize the various functions and methods. 
This approach is similar to that of the SimpleDrawingPlayer project, 
which utilized separate classes for different tools. 
By implementing this approach, we could extract similar methods from OfferingGUI and ListingAppGUI, 
minimizing the possibility of human error from copying and pasting the same code, as well as reducing clutter.