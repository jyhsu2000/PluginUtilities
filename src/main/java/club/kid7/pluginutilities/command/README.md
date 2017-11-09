# CommandComponent

Flexible nested command system

## Usage example
### Initialization in `onEnable()`
```java
CommandComponent bmCommand = new BannerMakerCommand(this);
getCommand("BannerMaker").setExecutor(bmCommand);
getCommand("BannerMaker").setTabCompleter(bmCommand);
```
### Command definition  
Reference to [BannerMaker](https://github.com/jyhsu2000/BannerMaker/tree/v1.8.1/src/main/java/tw/kid7/BannerMaker/command)
