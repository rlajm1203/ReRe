import styled from "styled-components";
import { MdArrowBackIosNew } from "react-icons/md";
import { BsTrash3 } from "react-icons/bs";
import { IoIosCheckboxOutline, IoIosSearch } from "react-icons/io";
import { IoCloseOutline } from "react-icons/io5";
import { HiOutlinePlus } from "react-icons/hi2";
import { VscDeviceCamera } from "react-icons/vsc";

export const StyledIcon = styled.span`
  height: 20px;
  font-size: ${(props) => props.size || "20px"};
  position: relative;
`;

export const Icon = ({ type, size, onClick }) => {
  const IconType = {
    search: <IoIosSearch />,
    back: <MdArrowBackIosNew />,
    trash: <BsTrash3 />,
    checkbox: <IoIosCheckboxOutline />,
    close: <IoCloseOutline />,
    plus: <HiOutlinePlus />,
    camera: <VscDeviceCamera />,
  };

  return (
    <StyledIcon size={size} onClick={onClick}>
      {IconType[type]}
    </StyledIcon>
  );
};
