# Planung
## Entities
User (id, name, email, pasword, bio)
Subscription (subscriberId, subscribedToId, timestamp)
FavoriteCat(id, catLoverId, imgUrl)

Post (id, content, timestamp, userId, postId (optional fÃ¼r comments))
Interaction (postId, type, timestamp)