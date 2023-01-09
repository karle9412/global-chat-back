from keras.models import Sequential
import data_preprocessing
import pandas as pd

result = []
text = data_preprocessing.preprocessing("여행가고 싶다")
result.append(text)
print(result)

clean_train_df = pd.DataFrame({'text': result})

DATA_IN_PATH = './'
TRAIN_CLEAN_DATA = 'test_text.csv'
clean_train_df.to_csv(DATA_IN_PATH + TRAIN_CLEAN_DATA, index = False)

