
import copy
import json
import mysql.connector

from validator import check_role, convert_to_pure_messages, to_hash_line

# 创建数据库连接
conn = mysql.connector.connect(
    host="rm-2zegsqig7356rf2rw.mysql.rds.aliyuncs.com",
    user="medical",
    password="DPhMtnP2ztJmvfzF",
    database="sft_data"
)

def cat_sessions():
    with conn.cursor() as cursor:
        cursor.execute("SELECT * FROM sessions")
        results = cursor.fetchall()
        for i, row in enumerate(results):
            if i>2:
                break
            print(f"{i}: {row}")

def add_collection():
    collection_name = "春雨仅提问切片"
    para = {"info": "春雨仅提问切片，来自于mw，13w"}
    insert_sql = "INSERT INTO session_collection (collection_name, para) VALUES (%s, %s)"
    with conn.cursor() as cursor:
        cursor.execute(insert_sql, (collection_name, json.dumps(para)))
        conn.commit()


def upload():
    insert_sql = "INSERT IGNORE INTO sessions (first_query, user_msg_hash, messages_raw, messages_pure, para, collection_id) VALUES (%s, %s, %s, %s, %s, %s)"
    batch_size = 100
    data_batch = []

    with open("/Users/guoxunpan/Downloads/train_data_processed_picked.jsonl", "r") as f, conn.cursor() as cursor:
        lines = f.readlines()
        for line in lines:
            data = json.loads(line)
            
            messages_raw = copy.deepcopy(data["messages"])
            is_role_right = check_role(messages_raw)
            if not is_role_right:
                continue
            messages_pure = convert_to_pure_messages(messages_raw)
            first_query = messages_pure[0]["content"] if messages_pure[0]["role"] == "user" else ''
            if not first_query:
                continue
            message_user_li = [msg["content"] for msg in messages_pure if msg["role"] == "user"]
            user_msg_hash = to_hash_line(message_user_li)
            para = {"messages_pure_length": len(messages_pure)}
            data_batch.append((first_query, user_msg_hash, json.dumps(messages_raw), json.dumps(messages_pure), json.dumps(para), 2))

            if len(data_batch) >= batch_size:
                cursor.executemany(insert_sql, data_batch)
                conn.commit()
                data_batch = []

        if data_batch:
            cursor.executemany(insert_sql, data_batch)
            conn.commit()

        cursor.close()


if __name__ == "__main__":
    upload()
    # add_collection()
    # cat_sessions()