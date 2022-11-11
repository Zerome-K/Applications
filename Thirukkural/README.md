## Thirukkural Application

   -> Thirukkural Application is a console project working with Thirukkural, the project holds useful usecases for the user.
   
   -> Application have features like 
                  ->  searching by kural, 
                  ->  search by topic, 
                  ->  search by chapter,
                  ->  search by Word,
                  ->  and a Thirukkural Game
                  
   -> The Game feature have the following workflow,
                  -> the program is setting by choose a single kural by random
                  -> after that the the words are shuffle and an empty spaces are given
                  -> users have to fill the blanks by choosing appropriate word from shuffled words,
                  -> if users arrange the words correctly , he then completed the task successfully and viceversa
                  
   -> The data of 1330 thirukkurals are stored in a JSON file which is a main source of the Application.

## Application Process and details

   -> The application follows the standard code structure with exception handling, Validation etc...
   
   -> Classes -> Main Class for beginning process,
              -> Application class handles the user interaction process
              -> ThirukkuralRunner class handles the business logic of the application.
   -> Some of the methods and functions 
              ->searchByWord() used for searching a verse by word
              ->kuralGame() is for thirukkural game
              ->searchByNmber() is for searching a kural by number.

## Technology Used
    - JAVA
    - JSON
