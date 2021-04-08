import matplotlib.pyplot as plt
import pandas as pd
import sys


def show_bar_plot(path_to_csv: str):
    df = pd.read_csv(path_to_csv)
    stats_df = pd.DataFrame({'KtElements': df.columns.values,
                             'Occurrence': df.values[0]})
    ax = stats_df.plot.barh(x='KtElements', y='Occurrence')
    for p, v in zip(ax.patches, df.values[0]):
        ax.annotate(str(v), (v + p.get_x(), p.get_y()))
    plt.title(path_to_stats)
    plt.tight_layout()
    plt.show()


if __name__ == '__main__':
    path_to_stats = sys.argv[1]
    show_bar_plot(path_to_stats)
