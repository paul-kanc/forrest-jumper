package game;

import city.cs.engine.SoundClip;
import game.Character.CharacterController;
import game.Heart.Lives;
import game.HighScores.TimeScore;
import game.Levels.*;
import game.Reaper.GhostlyReaperCollision;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GameSaverLoader {

    public static void save(GameLevel level, String fileName) throws IOException {
        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "," +
                    Lives.getLives() + "," + TimeScore.getScore() + "\n");
        } finally {
            if (writer !=null) {
                writer.close();
            }
        }
    }

    public static void load(String fileName) throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading" + fileName);
            fr = new FileReader((fileName));
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            String[] tokens = line.split(",");
            String level = tokens[0];
            int lives = Integer.parseInt(tokens[1]);
            int score = Integer.parseInt(tokens[2]);

            Game.getGameMusic().stop();

            if (level.equals("Level2")) {
                Game.setLevel(new GameLevel1());
                GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            }

            else if (level.equals("Level3")) {
                Game.setLevel(new GameLevel2());
                GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            }

            else if (level.equals("Level4")) {
                Game.setLevel(new GameLevel3());
                GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            }

            else if (level.equals("Level5")) {
                Game.setLevel(new GameLevel4());
                GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            }

            else if (level.equals("Level6")) {
                Game.setLevel(new GameLevel5());
                GhostlyReaperCollision.setRespawn(new Vec2(-25, -15.5f));
                GameView.setCoin(new ImageIcon("data/coin.png").getImage());
            }

            else if (level.equals("Level7")) {
                Game.setLevel(new GameLevel6());

                try {
                    Game.setJungleSound(new SoundClip("data/sounds/rock.wav"));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    System.out.println(e);
                }
                Game.getJungleSound().setVolume(0.3);
                GameView.setCoin(new ImageIcon("data/skull.png").getImage());
                CharacterController.setBomb(true);
            }

            else if (level.equals("Level8")) {
                Game.setLevel(new GameLevel7());

                try {
                    Game.setJungleSound(new SoundClip("data/sounds/rock.wav"));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    System.out.println(e);
                }
                Game.getJungleSound().setVolume(0.3);
                GameView.setCoin(new ImageIcon("data/skull.png").getImage());
                CharacterController.setBomb(true);
            }

            else if (level.equals("Level9")) {
                Game.setLevel(new GameLevel8());

                try {
                    Game.setJungleSound(new SoundClip("data/sounds/rock.wav"));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    System.out.println(e);
                }
                Game.getJungleSound().setVolume(0.3);
                GameView.setCoin(new ImageIcon("data/skull.png").getImage());
                CharacterController.setBomb(true);
            }

            else if (level.equals("Level10")) {
                Game.setLevel(new GameLevel9());

                try {
                    Game.setJungleSound(new SoundClip("data/sounds/rock.wav"));
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                    System.out.println(e);
                }
                Game.getJungleSound().setVolume(0.3);
                GameView.setCoin(new ImageIcon("data/skull.png").getImage());
                CharacterController.setBomb(true);
            }

            if (!level.equals("Level1")) {
                Lives.setLives(lives);
                Game.getJungleSound().loop();
            }

            if (lives == 1) {
                GameView.setHeart1(GameView.getHeart());
            }

            else if (lives == 2) {
                GameView.setHeart1(GameView.getHeart());
                GameView.setHeart2(GameView.getHeart());
            }

            else if (lives == 3) {
                GameView.setHeart1(GameView.getHeart());
                GameView.setHeart2(GameView.getHeart());
                GameView.setHeart3(GameView.getHeart());
            }

            else if (lives == 4) {
                GameView.setHeart1(GameView.getHeart());
                GameView.setHeart2(GameView.getHeart());
                GameView.setHeart3(GameView.getHeart());
                GameView.setHeart4(GameView.getHeart());
            }

            else if (lives == 5) {
                GameView.setHeart1(GameView.getHeart());
                GameView.setHeart2(GameView.getHeart());
                GameView.setHeart3(GameView.getHeart());
                GameView.setHeart4(GameView.getHeart());
                GameView.setHeart5(GameView.getHeart());
            }

            TimeScore.setScore(score);
            TimeScore.getTime().start();
            Game.goToNextLevel();
        } finally {
            if (reader != null) {
                reader.close();
            }

            if (fr != null) {
                fr.close();
            }
        }
    }
}
