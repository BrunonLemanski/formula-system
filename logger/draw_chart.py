import pandas as pd

d = {"sell": [
    {
        "Rate": 0.001425,
        "Quantity": 537.27713514
    },
    {
        "Rate": 0.00142853,
        "Quantity": 6.59174681
    }
]}

df = pd.DataFrame(d['sell'])
print(df)
Quantity      Rate
0  537.277135  0.001425
1    6.591747  0.001429

df.plot(x='Quantity', y='Rate')
