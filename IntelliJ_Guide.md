# How to start Thunder Games Mole Company in IntelliJ

## Step 1

Add the following 3 configurations to "Run/Debug configurations".
All of this configuration should be build under Java 11.

    Config Name:        Server
    Main Class:         de.thundergames.MoleGames
    Program arguments:  s

    Config Name:        AI
    Main Class:         de.thundergames.MoleGames
    Program arguments:  "a" <ip> <port>  <gameID>
    Info:               Under "Modify options" select "Allow multiple instances.

    Config Name:        Player
    Main Class:         de.thundergames.MoleGames
    Program arguments:  p

## Step 2

Select the Server configuration and start it.

## Step 3

Select the AI configuration and start it.
You can do it multiple times until the game is full.

## Step 4

Select the Player configuration and start it.
Logindata:
Name: [insert your name]
IP: 127.0.0.1
Port: 5000
