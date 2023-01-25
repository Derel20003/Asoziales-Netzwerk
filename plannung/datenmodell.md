# Planung
## Entities
User (id, name, email, pasword, bio)
Subscription (subscriberId, subscribedToId, timestamp)
Post (id, content, timestamp, userId, postId (optional fÃ¼r comments))
Interaction (postId, type, timestamp)
FavoriteCat(id, catLoverId, imgUrl)