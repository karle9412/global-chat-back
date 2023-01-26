import data_preprocessing
import pandas as pd
import oracledb

def test_text(email):
    email = "\'" + email +"\'"
    print(email)
    con = oracledb.connect(user="c##oracle_chat", password="1234", dsn="localhost:1521/xe")
    cursor = con.cursor()
    sql = ""
    sql += "select CHAT_CONTENT from CHAT_CONTENT_TB WHERE EMAIL=" + email
    print(sql)

    cursor.execute(sql)
    out_data = cursor.fetchmany()

    print(out_data)

    result = []
    for i in out_data:
        i = i[0]
        text = data_preprocessing.preprocessing(i)
        result.append(text)

    con.close()
    print(result)

    clean_train_df = pd.DataFrame({'text': result})

    DATA_IN_PATH = './'
    TRAIN_CLEAN_DATA = 'test_text.csv'
    clean_train_df.to_csv(DATA_IN_PATH + TRAIN_CLEAN_DATA, index = False)



