package escape.code.core;

import escape.code.controllers.PuzzlesController;
import escape.code.models.Puzzle;
import escape.code.userInterface.Reader;

import java.util.LinkedList;

public class PuzzleManager {

    private LinkedList<Puzzle> puzzles;
    private Reader reader;


    public PuzzleManager(Reader reader) {
        this.puzzles = new LinkedList<>();
        this.reader = reader;
    }

    public void load(){
        LinkedList<String> puzzleImages = new LinkedList<String>(){{
            add("/pictures/ComputerTaskWhite.png");
            add("/pictures/PianoTask.jpg");
            add("/pictures/LibraryWithJoker.jpg");
        }};
        LinkedList<String> puzzles = reader.readPuzzleFile();
        for (String puzzle : puzzles) {
            String[] line = puzzle.split("\\*\\*");
            String description = line[0];
            String answer = line[1];
            String hint = line[2];
            String nextClue = line[3];
            Puzzle currentPuzzle = new Puzzle(description,answer, hint,puzzleImages.pop(), nextClue);
            this.puzzles.add(currentPuzzle);
        }

    }

    public void setPuzzle(){

        if (puzzles.size() > 0){
            PuzzlesController.setPuzzle(puzzles.pop());
        }
    }

}
