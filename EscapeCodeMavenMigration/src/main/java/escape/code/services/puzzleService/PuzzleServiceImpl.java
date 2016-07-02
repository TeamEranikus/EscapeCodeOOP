package escape.code.services.puzzleService;

import escape.code.daos.puzzledaos.PuzzleDao;
import escape.code.daos.puzzledaos.PuzzleDaoImpl;
import escape.code.models.Puzzle;

public class PuzzleServiceImpl implements PuzzleService {

     private PuzzleDao puzzleDao = new PuzzleDaoImpl();


    @Override
    public void createPuzzle(String... params) {
        Puzzle puzzle = new Puzzle();
        puzzle.setQuestion(params[0]);
        puzzle.setCorrectAnswer(params[1]);
        puzzle.setHint(params[2]);
        puzzle.setNextClue(params[3]);
        puzzle.setImagePath(params[4]);
        puzzle.setLevelName(params[5]);

        this.puzzleDao.createPuzzle(puzzle);
    }
}
