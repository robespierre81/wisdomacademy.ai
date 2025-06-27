import rlp
from eth_utils import to_bytes, encode_hex

# List of validator addresses (Ensure these are correct)
validators = [
    "0x5ae47b3d0206fa42386189cf33bc21e3938a0386"
]

# Proposer address (Must be one of the validators)
proposer = "0x5ae47b3d0206fa42386189cf33bc21e3938a0386"

# Encode the fields using RLP encoding
extra_data = rlp.encode([
    b'\x00' * 32,  # Empty seals (32 bytes)
    [to_bytes(hexstr=v) for v in validators],  # Validators list
    to_bytes(hexstr=proposer),  # Proposer address
    b'\x00',  # Round number
    []  # Empty seals
])

# Print the correctly formatted `extraData`
print("Generated extraData:", encode_hex(extra_data))
