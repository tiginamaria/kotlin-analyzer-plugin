# kotlin-analyzer-plugin
Plugin for kotlin files structure analysis. The current version provide abilities to collect occurrence statistics of all element types PSI tree.

## Run Statistics Collection Plugin
1. Open project in Intellij IDEA
2. Execute run configuration **Run Plugin**
3. In **Project View Popup Menu** open **File Menu** of any Kotlin file and select **Collect Statistics** action
4. Select directory to save statistics repost file there in the appeared dialog window
5. After the plugin exits, file *SelectedKotlinFileNameStats.csv* will appear in selected directory 

## Run Statistics Visualisation
1. In command line from root directory run `pip install -r requirements.txt`
2. Go to python scripts directory `cd src/main/python/`
3. In command line run `python stats_visualisation.py path/to/csv/with/statistics/report`
4. Script will produce bar chart with collected occurrence statistics 