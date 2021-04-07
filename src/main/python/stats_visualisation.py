import matplotlib.pyplot as plt
import pandas as pd
import sys

if __name__ == '__main__':
    path_to_stats = sys.argv[1]
    df = pd.read_csv(path_to_stats)
    stats_df = pd.DataFrame({'KtElements': df.columns.values,
                             'Occurrence': df.values[0]})
    stats_df.plot.barh(x='KtElements', y='Occurrence')
    plt.tight_layout()
    plt.title(path_to_stats)
    plt.show()
