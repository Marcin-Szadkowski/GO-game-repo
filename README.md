# GO game
Repository for GO game project. Go is an abstract strategy board game for two players,
in which the aim is to surround more territory than the opponent.
* [Info about game](https://en.wikipedia.org/wiki/Go_(game))

## Design
App was designed on the strength of simply design patterns such as adapter, singleton or builder. 
Great level of abstraction gives ability to replace independent modules, for example connectors or GUI. 
At this stage app is set to stream game through localhost, but the connector can be changed. 

## Lobby
App includes lobby that connects players automatically when they are ready. 

## Bot (Computer player)
If lobby is empty player can play with bot. Computer player follows simple algorithm that tries to take
all breaths from enemy`s stones.

## Testing
Generally over 70% of code is tested using Mockito framework. 

## Authors
* **Wojciech Wróblewski** - *Client*
* **Marcin Szadkowski** - *Server*
