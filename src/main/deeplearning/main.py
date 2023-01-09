import pandas as pd

df = pd.read_csv('test_bag.csv')

print(df["category"].value_counts())