
import hashlib
import json


def check_role(messages_raw):
    messages = messages_raw
    role_li = [msg["role"] for msg in messages if msg["role"] in ["user", "assistant"]]
    
    for i in range(len(role_li)):
        if i == 0: continue
        if role_li[i] == role_li[i - 1]:
            return False
    return True


def convert_to_pure_messages(messages_raw):
    messages = messages_raw
    messages_u_a = [msg for msg in messages if msg["role"] in ["user", "assistant"]]
    if messages_u_a[-1]["role"] == "assistant":
        messages_u_a.pop()
    return messages_u_a

def to_hash_line(msg_li):
    all_cut_msg = ""
    for m in msg_li:
        all_cut_msg = all_cut_msg + m
    return hashlib.sha256(str(all_cut_msg).encode()).hexdigest()

def to_hash_set(file_path):
    with open(file_path, "r") as f:
        all_cut_set = []
        lines = f.readlines()
        for line in lines:
            all_cut_msg = ""
            msg = json.loads(line)["messages"]
            for m in msg:
                if m["role"] == "user":
                    all_cut_msg = all_cut_msg + m["content"]
            all_cut_set.append(hashlib.sha256(
                str(all_cut_msg).encode()).hexdigest())
        return set(all_cut_set)
