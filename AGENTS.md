# AGENTS.md - craps-game

## Stack
- Java 9 (maven-compiler-plugin source/target 9)
- JavaFX 21 (controls, fxml) via javafx-maven-plugin 0.0.8
- JUnit Jupiter 5.12.1 (test scope)
- Module: `com.example.crapsgame262` (`src/main/java/module-info.java`)

## Entry points
- Launcher: `com.example.crapsgame262.Launcher` (`src/main/java/com/example/crapsgame262/Launcher.java`) calls `Application.launch(HelloApplication.class, ...)`
- App: `com.example.crapsgame262.HelloApplication` extends `Application`; `start()` creates `WelcomeView.getInstance()` and calls `show()` (FXML load commented)
- Views: `com.example.crapsgame262.views.{WelcomeView, GameView}` extend `Stage`, load FXML from classpath `/com/example/crapsgame262/{welcome,game}-view.fxml`, use singleton holders
- Controllers: `controllers.{WelcomeController, GameController}`; `GameController` obtained via `GameView.getController()` and `setCurrentPlayer(Player)`
- Resources: FXML under `src/main/resources/com/example/crapsgame262/`, dice images under `images/dices/dice{n}.png` (loaded by `Dice.getImagePath()`)

## Commands (Maven)
- Run app: `./mvnw javafx:run` (or `mvn javafx:run`). Main class configured as `com.example.crapsgame262/com.example.crapsgame262.HelloApplication` in `pom.xml`.
- Compile: `./mvnw compile`
- Test: `./mvnw test` (JUnit 5; no tests present under `src/test` currently)
- Package: `./mvnw package`

## Notes / gotchas
- Module opens/exports: `com.example.crapsgame262` and `com.example.crapsgame262.controllers` to `javafx.fxml` (see `module-info.java`). FXML controllers must be in opened packages.
- FXML resource paths in views use leading `/com/example/crapsgame262/...` (absolute from classpath). Images loaded via `getClass().getResourceAsStream(dice.getImagePath())` where `Dice.getImagePath()` returns `/com/example/crapsgame262/images/dices/dice{value}.png`.
- Welcome flow: on start click, `WelcomeController` creates `Player`, gets `GameView.getInstance()`, calls `gameView.getController().setCurrentPlayer(player)`, shows game view, hides welcome window. Empty nickname shows `AlertBox`.
- Singleton pattern used for `WelcomeView` and `GameView` (static holder).
- JavaFX plugin runs with modular main class (`com.example.crapsgame262/com.example.crapsgame262.HelloApplication`).
