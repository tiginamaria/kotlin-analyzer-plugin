# kotlin-analyzer-plugin
Plugin for Kotlin files structure analysis. The current version provide abilities to collect occurrence statistics of all element types PSI tree. 
Clone and run plugin project or download and import zip from last build in [github actions](https://github.com/tiginamaria/kotlin-analyzer-plugin/actions) and then visualize collected statistics.

## Run Statistics Collection Plugin on IDEA
1. Clone this repo
2. Open project in Intellij IDEA
3. Execute run configuration **Run Plugin**
4. In **Project View Popup Menu** open **File Menu** of any Kotlin file and select **Collect Statistics** action
5. Select directory to save statistics repost file there in the appeared dialog window
6. After the plugin exits, file *SelectedKotlinFileNameStats.csv* will appear in selected directory 
![statistics collection](https://raw.githubusercontent.com/wiki/tiginamaria/kotlin-analyzer-plugin/images/ActionCollectStatistics.png)

## Add Plugin from ZIP to IDEA
1. Install *plugin-artifact.zip* from last build in [github actions](https://github.com/tiginamaria/kotlin-analyzer-plugin/actions)
2. Go to File > Settings > Plugins > Install Plugin from Disk...
3. Reload IDEA

## Run Statistics Visualization
1. In command line from root directory run `pip install -r requirements.txt`
2. Go to python scripts directory `cd src/main/python/`
3. In command line run `python stats_visualization.py path/to/csv/with/statistics/report`
4. Script will produce bar chart with collected occurrence statistics 
![statistics visualization](https://raw.githubusercontent.com/wiki/tiginamaria/kotlin-analyzer-plugin/images/FigureOpenClass.png)
