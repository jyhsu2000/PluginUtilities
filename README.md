# PluginUtilities
[![](https://jitpack.io/v/jyhsu2000/PluginUtilities.svg)](https://jitpack.io/#jyhsu2000/PluginUtilities)
[![license](https://img.shields.io/github/license/jyhsu2000/PluginUtilities.svg)](https://github.com/jyhsu2000/PluginUtilities/blob/master/LICENSE)

Utilities for Bukkit/Spigot plugin

## Components

- [KItemStack](./src/main/java/club/kid7/pluginutilities/kitemstack) - Chainable ItemStack for ItemStacks in Bukkit
- [KConfigManager](./src/main/java/club/kid7/pluginutilities/configuration) - Config Manager
- [CustomGUI](./src/main/java/club/kid7/pluginutilities/gui) - Custom GUI inventory menu
- [CommandComponent](./src/main/java/club/kid7/pluginutilities/command) - Flexible nested command system

## Install

**Step 1.** Add the JitPack repository to your build file.
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

**Step 2.** Add the dependency.  
> You can change the `version` property as you want.  
> All available version can be found [here](https://jitpack.io/#jyhsu2000/PluginUtilities).

```xml
<dependency>
    <groupId>com.github.jyhsu2000</groupId>
    <artifactId>PluginUtilities</artifactId>
    <version>1.6.2</version>
</dependency>
```

**Step 3.** Add the shading plugin to the build.  
Replace `YOUR.OWN.PACKAGE` with your own package.
```xml
<build>
     <plugins>
         <plugin>
             <groupId>org.apache.maven.plugins</groupId>
             <artifactId>maven-shade-plugin</artifactId>
             <version>2.3</version>
             <configuration>
                 <artifactSet>
                     <includes>
                         <include>com.github.jyhsu2000:PluginUtilities</include>
                     </includes>
                 </artifactSet>
                 <relocations>
                     <relocation>
                         <pattern>club.kid7.pluginutilities</pattern>
                         <shadedPattern>YOUR.OWN.PACKAGE</shadedPattern>
                     </relocation>
                 </relocations>
             </configuration>
             <executions>
                 <execution>
                     <phase>package</phase>
                     <goals>
                         <goal>shade</goal>
                     </goals>
                 </execution>
             </executions>
         </plugin>
     </plugins>
 </build>
```
