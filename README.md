# Tic Tac Toe game for two players

### Project tasks
- Adding git support DONE
- Adding Maven support DONE
- Adding README.md DOEN
- What the game does DONE
- How to run DONE
- List of tests
- Adding .gitignore DONE
- CRC
- At least 60% test coverage: 85% classes, 59% method, 52% lines

### Functional requirements
- It is "best of three", though I can quit mid-way through. DONE
- Characters: O (naught) and X (cross). DONE
- Players have names and scores. DONE
- Winner has better score. Draw is possible. DONE
- Interactive:
    - it should accept players instructions about each move DONE
    - it should ask who begins DONE
    - it informs about session result, who’s turn it is now and the like DONE
- Match gives points: win 3, draw 1, loss 0. 3 matches == game. DONE
- Game works with square or rectangular board. DONE
- Player wins, if he has unbroken line of his characters, in a row, in a column or diagonally. DONE
- Winning is announced in a message: Wygrywa O. O: 1 X: 0 (numbers are current points). DONE
- Game is configurable:
    - Board dimensions: 3x3, 4x4, 99x101, etc. (user provides) DONE
    - Winning condition has variable number of characters: 3, 4, 5, etc. (user provides) DONE
    - Game messages should have configurable target: console (System.out) or logs (for the sake of this exercise it’s OK to make it System.err), or external printer. DONE
    - before game starts it asks who goes first, O or X DONE
    - We are bi-lingual: Polish and English are fine. In future we want to add more languages: messages are to be read from a file for chosen language. Choosing the language depends on configuration variable. DONE

### Extra requirements
- GUI
- I can play against computer
- I can play through network (not on one machine)

### Running
- Go to: TicTacToeMvn\target\classes\
- Run: java io.github.devas.game.Main

### List of tests
- DoesTurnStatusTellIfGameIsWonOrNotWon
- testTwoBoardMovesAreEqual
- TwoBoardMovesAreNotEqual
- CalculationOfBoardAreas
- IfBoardResetsItselfWithDefaultValue
- IfWholeBoardIsFilledWithSpecifiedString
- BoardSetValueAndGetValueForPrimitives
- BoardSetValueAndGetValueForPosition
- FillingBoardWithAlphabet
- IsStringWithBoardRepresentationCorrect
- IsStringWithDiagonalsCorrect
- DoesGameWinningCount
- CompareTwoPlayersByName
- AddingGettingResettingPlayersMoves
- ArePlayersResultsCorrectlyComputed
- ArePlayersCorrectlyComparedBasedOnResults
- IsGameInitiallyNotFinished
- IsGameFinished
- IsCurrentPlayerCorrect
- IsCurrentPlayerCorrectAfterSeveralTurns
