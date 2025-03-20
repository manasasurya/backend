-- Insert default admin user with encoded password (password is 'admin123')
INSERT INTO users (name, email, password, role) VALUES
('Admin', 'admin@gmail.com', '$2a$10$vwzE5mJlVqbOLwTdQHp5iOeKxFVPsGAEW.Qr7Hm8P0nXwVzqzwUFe', 'ADMIN');

-- Insert popular tourist destinations
INSERT INTO destinations (name, description, image_url, rating, review_count, location) VALUES
-- International Destinations
('Taj Mahal', 'An ivory-white marble mausoleum on the right bank of the river Yamuna, commissioned in 1632 by the Mughal emperor Shah Jahan. One of the New Seven Wonders of the World.', 'https://images.unsplash.com/photo-1564507592333-c60657eea523?q=80&w=1000', 4.8, 150, 'Agra, Uttar Pradesh, India'),
('Eiffel Tower', 'A wrought-iron lattice tower on the Champ de Mars in Paris, France. Named after engineer Gustave Eiffel.', 'https://images.pexels.com/photos/1530259/pexels-photo-1530259.jpeg', 4.7, 200, 'Paris, France'),
('Great Wall of China', 'An ancient series of walls and fortifications located in northern China, built around 500 years ago.', 'https://images.pexels.com/photos/4445240/pexels-photo-4445240.jpeg', 4.9, 180, 'Beijing, China'),

-- Indian Historical Sites
('Hawa Mahal', 'The Palace of Winds is a five-story palace built in 1799, featuring 953 windows and a distinctive honeycomb design.', 'https://images.pexels.com/photos/4476397/pexels-photo-4476397.jpeg', 4.6, 120, 'Jaipur, Rajasthan, India'),
('Amber Fort', 'A majestic fort-palace complex built from red sandstone and marble, featuring stunning architecture and scenic views.', 'https://images.pexels.com/photos/2845643/pexels-photo-2845643.jpeg', 4.7, 140, 'Jaipur, Rajasthan, India'),
('Qutub Minar', 'A UNESCO World Heritage site, this 73-meter tall minaret was built in the 12th century and showcases Indo-Islamic architecture.', 'https://images.pexels.com/photos/13585045/pexels-photo-13585045.jpeg', 4.5, 110, 'Delhi, India'),

-- Indian Spiritual Sites
('Golden Temple', 'The holiest shrine of Sikhism, known for its stunning golden architecture and spiritual atmosphere.', 'https://images.pexels.com/photos/4267471/pexels-photo-4267471.jpeg', 4.9, 180, 'Amritsar, Punjab, India'),
('Varanasi Ghats', 'Ancient riverside steps along the Ganges River, central to Hindu spiritual practices and ceremonies.', 'https://images.pexels.com/photos/16542959/pexels-photo-16542959.jpeg', 4.7, 160, 'Varanasi, Uttar Pradesh, India'),
('Khajuraho Temples', 'Famous for their nagara-style architectural symbolism and erotic sculptures, built between 950-1050 CE.', 'https://images.pexels.com/photos/7184626/pexels-photo-7184626.jpeg', 4.6, 90, 'Khajuraho, Madhya Pradesh, India'),

-- Indian Natural Wonders
('Valley of Flowers', 'A national park known for its meadows of endemic alpine flowers and variety of flora, located in the West Himalaya.', 'https://images.pexels.com/photos/2406452/pexels-photo-2406452.jpeg', 4.8, 85, 'Uttarakhand, India'),
('Thar Desert', 'A large arid region forming a natural boundary between India and Pakistan, known for its sand dunes and culture.', 'https://images.unsplash.com/photo-1593096380974-6d508c2b1791?q=80&w=1000', 4.4, 75, 'Rajasthan, India'),
('Sundarbans', 'The largest mangrove forest in the world and home to the Bengal tiger, declared a UNESCO World Heritage Site.', 'https://images.unsplash.com/photo-1598425716574-07386ce45df5?q=80&w=1000', 4.7, 95, 'West Bengal, India'),

-- Indian Beach Destinations
('Goa Beaches', 'Famous for its pristine beaches, nightlife, and Portuguese architecture, Goa is India\'s premier beach destination.', 'https://images.unsplash.com/photo-1512343879784-a960bf40e7f2?q=80&w=1000', 4.6, 200, 'Goa, India'),
('Andaman Islands', 'Known for their pristine beaches, coral reefs, and marine life, perfect for snorkeling and scuba diving.', 'https://images.unsplash.com/photo-1589308955174-09ee1a8c2f19?q=80&w=1000', 4.8, 130, 'Andaman and Nicobar Islands, India'),
('Varkala Beach', 'A stunning beach with dramatic cliffs, known for its mineral springs and spectacular sunsets.', 'https://images.unsplash.com/photo-1590050752117-238cb0fb12b1?q=80&w=1000', 4.5, 110, 'Kerala, India'),

-- Indian Hill Stations
('Darjeeling', 'Famous for its tea plantations, toy train, and views of Mount Kanchenjunga, the world\'s third-highest peak.', 'https://images.unsplash.com/photo-1544634076-a90160ddf44d?q=80&w=1000', 4.7, 140, 'West Bengal, India'),
('Shimla', 'The former summer capital of British India, known for its colonial architecture and scenic beauty.', 'https://images.unsplash.com/photo-1626621341517-bbf3d9990a23?q=80&w=1000', 4.6, 160, 'Himachal Pradesh, India'),
('Ooty', 'Queen of hill stations, famous for its tea gardens, toy train, and pleasant weather throughout the year.', 'https://images.unsplash.com/photo-1629397586475-61c56631c038?q=80&w=1000', 4.5, 150, 'Tamil Nadu, India'),

-- Indian Cultural Sites
('Mysore Palace', 'A historical palace known for its Indo-Saracenic architecture and the famous Mysore Dasara festival.', 'https://images.unsplash.com/photo-1600689512986-56c4aa4d9146?q=80&w=1000', 4.8, 170, 'Mysore, Karnataka, India'),
('Hampi', 'Ancient ruins of the Vijayanagara Empire, featuring stunning temple complexes and boulder-strewn landscapes.', 'https://images.unsplash.com/photo-1600100397608-f010a8e6d3f3?q=80&w=1000', 4.9, 140, 'Karnataka, India'),
('Ajanta Caves', 'Ancient Buddhist caves known for their remarkable rock-cut architecture and paintings dating from the 2nd century BCE.', 'https://images.unsplash.com/photo-1623476408624-721c9185d569?q=80&w=1000', 4.7, 120, 'Maharashtra, India'),

-- Indian Wildlife Sanctuaries
('Ranthambore', 'One of India\'s largest national parks, famous for its Bengal tigers and ancient ruins.', 'https://images.unsplash.com/photo-1581792761273-3d2082a8d1c7?q=80&w=1000', 4.6, 130, 'Rajasthan, India'),
('Kaziranga', 'Home to two-thirds of the world\'s one-horned rhinoceros population and diverse wildlife.', 'https://images.unsplash.com/photo-1582652647283-2d3d30dce0bf?q=80&w=1000', 4.8, 110, 'Assam, India'),
('Jim Corbett', 'India\'s oldest national park, known for its rich biodiversity and tiger conservation efforts.', 'https://images.unsplash.com/photo-1585468274952-0b9a01b77b1a?q=80&w=1000', 4.7, 140, 'Uttarakhand, India');
